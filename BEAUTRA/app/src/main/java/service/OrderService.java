package service;

import model.CartItem;
import model.Order;
import model.Product;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private static final String FILE_PATH = "src/main/resources/data/orders.json";

    public void createOrder(List<CartItem> cart, String buyerId) {
        List<Order> orders = JsonUtil.readJson(FILE_PATH, Order.class);
        ProductService productService = new ProductService();

        // Validasi dan update stok produk
        List<CartItem> validItems = new ArrayList<>();
        for (CartItem ci : cart) {
            Product product = productService.getProductById(ci.getProductId());
            if (product != null && product.getStock() >= ci.getQuantity()) {
                // Update stok produk
                Product updatedProduct = new Product(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getBrand(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock() - ci.getQuantity(), // Kurangi stok
                        product.getImage(),
                        product.getSellerId()
                );

                // Update produk di database
                List<Product> allProducts = productService.getAllProducts();
                for (int i = 0; i < allProducts.size(); i++) {
                    if (allProducts.get(i).getId().equals(product.getId())) {
                        productService.updateProduct(i, updatedProduct);
                        break;
                    }
                }

                // Menambahkan produk yang valid
                validItems.add(new CartItem(ci.getProductId(), ci.getQuantity(), product.getPrice()));
            }
        }

        if (validItems.isEmpty()) {
            throw new RuntimeException("Tidak ada produk yang tersedia atau stok tidak mencukupi!");
        }

        // Membuat order baru
        Order newOrder = new Order(
                "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                buyerId,
                validItems,
                "", // Note
                "", // Voucher
                "JNE", // Shipping
                "COD", // Payment method
                validItems.stream().mapToDouble(ci -> ci.getPrice() * ci.getQuantity()).sum(), // Total harga
                "pending", // Status
                java.time.LocalDateTime.now().toString() // Timestamp
        );

        // Menambahkan order ke daftar orders
        orders.add(newOrder);
        JsonUtil.writeJson(FILE_PATH, orders);
        
    }


    // Method untuk mengambil semua orders
    public List<Order> getAllOrders() {
        return JsonUtil.readJson(FILE_PATH, Order.class);
    }

    // Method untuk mengambil order berdasarkan id
    public Order getOrderById(String orderId) {
        List<Order> orders = JsonUtil.readJson(FILE_PATH, Order.class);
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    // Method untuk menghitung total dari daftar pesanan
    public double calculateTotal(List<CartItem> cart) {
        return cart.stream().mapToDouble(ci -> ci.getPrice() * ci.getQuantity()).sum();
    }
}
