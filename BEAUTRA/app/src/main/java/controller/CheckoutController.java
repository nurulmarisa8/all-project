package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CartItem;
import model.Product;
import service.OrderService;
import service.ProductService;
import util.AlertUtil;

import java.util.List;

public class CheckoutController {
    @FXML private TextField nameField, phoneField, addressField;
    @FXML private ComboBox<String> paymentBox;
    @FXML private VBox orderSummaryBox;
    @FXML private Label subtotalLabel, shippingLabel, totalLabel;
    @FXML private Button placeOrderBtn;

    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();
    private static final double SHIPPING_COST = 15000;

    private List<CartItem> cart;

    public void checkout(List<CartItem> cart2, String buyerId) {
        this.cart = cart2;
        updateOrderSummary();

        paymentBox.getItems().setAll("COD", "QRIS", "Transfer Bank");
        paymentBox.getSelectionModel().selectFirst();

        placeOrderBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || addressField.getText().isEmpty()) {
                AlertUtil.showInfo("Lengkapi data pengiriman!");
                return;
            }
            orderService.createOrder(cart2, buyerId);
            AlertUtil.showInfo("Pesanan berhasil dibuat!");
            placeOrderBtn.getScene().getWindow().hide();
        });
    }


    private void updateOrderSummary() {
        orderSummaryBox.getChildren().clear();
        double subtotal = 0;
        for (CartItem ci : cart) {
            Product p = productService.getAllProducts().stream()
                .filter(prod -> prod.getId().equals(ci.getProductId()))
                .findFirst().orElse(null);

            HBox row = new HBox(12);
            Label name = new Label((p != null ? p.getName() : ci.getProductId()) + " x" + ci.getQuantity());
            name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            Label price = new Label("Rp" + String.format("%,.0f", ci.getPrice() * ci.getQuantity()));
            price.setStyle("-fx-font-size: 16px;");
            row.getChildren().addAll(name, price);
            orderSummaryBox.getChildren().add(row);

            subtotal += ci.getPrice() * ci.getQuantity();
        }
        subtotalLabel.setText("Rp" + String.format("%,.0f", subtotal));
        shippingLabel.setText("Rp" + String.format("%,.0f", SHIPPING_COST));
        totalLabel.setText("Rp" + String.format("%,.0f", subtotal + SHIPPING_COST));
    }

    @FXML
    private void updateProductStock(List<CartItem> cart) {
        // Mengupdate stok produk setelah pesanan selesai
        ProductService productService = new ProductService();

        // Looping untuk setiap item yang dibeli
        for (CartItem item : cart) {
            Product product = productService.getProductById(item.getProductId());
            if (product != null) {
                int newStock = product.getStock() - item.getQuantity();

                // Pastikan stok tidak negatif
                if (newStock < 0) {
                    AlertUtil.showInfo("Stok tidak mencukupi untuk produk " + product.getName());
                    return; // Jika stok kurang, batalkan transaksi
                }

                // Update produk dengan stok yang baru
                product.setStock(newStock);
                productService.updateProduct(newStock, product);
            }
        }

    // Update produk-produk yang sudah terupdate stoknya
    AlertUtil.showInfo("Pembayaran berhasil! Stok telah diperbarui.");
}

}
