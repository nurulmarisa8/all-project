package controller;

import beautra.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Order;
import model.Product;
import model.CartItem;
import service.OrderService;
import service.ProductService;
import util.AlertUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.stage.FileChooser;

public class SellerDashboardController {
    @FXML private ListView<String> productList;
    @FXML private TableView<OrderRow> orderTable;
    @FXML private Button logoutBtn;  // Deklarasi variabel untuk tombol logout

    private final ProductService productService = new ProductService();
    private final OrderService orderService = new OrderService();
    private List<Product> myProducts;

    @FXML
    public void initialize() {
        loadProducts();
        loadOrders();
    }

    private void loadProducts() {
        myProducts = productService.getAllProducts().stream()
            .filter(p -> p.getSellerId() != null && p.getSellerId().equals(MainApp.currentUser.getId()))
            .collect(Collectors.toList());
        productList.getItems().clear();
        for (Product p : myProducts) {
            productList.getItems().add(p.getName() + " | Stok: " + p.getStock() + " | Rp" + p.getPrice());
        }
    }

    private void loadOrders() {
        ObservableList<OrderRow> data = FXCollections.observableArrayList();
        List<Order> allOrders = orderService.getAllOrders();
        for (Order o : allOrders) {
            for (CartItem item : o.getItems()) {
                Product p = productService.getAllProducts().stream()
                    .filter(prod -> prod.getId().equals(item.getProductId()) && prod.getSellerId().equals(MainApp.currentUser.getId()))
                    .findFirst().orElse(null);
                if (p != null) {
                    data.add(new OrderRow(
                        o.getId(),
                        p.getName(),
                        item.getQuantity(),
                        item.getPrice() * item.getQuantity(),
                        o.getStatus() != null ? o.getStatus() : "-"
                    ));
                }
            }
        }
        orderTable.setItems(data);
    }

    @FXML
    private void showAddProductDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Tambah Produk");

        TextField nameField = new TextField();
        TextField stokField = new TextField();
        TextField hargaField = new TextField();

        ComboBox<String> kategoriBox = new ComboBox<>();
        kategoriBox.getItems().addAll("SkinCare", "BodyCare", "Hair Care", "Make Up", "Lainnya");

        TextArea deskripsiField = new TextArea();

        Button pilihGambarBtn = new Button("Pilih Gambar");
        Label gambarLabel = new Label();
        final String[] imagePath = {""};

        pilihGambarBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar Produk");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(dialog.getDialogPane().getScene().getWindow());
            if (selectedFile != null) {
                try {
                    // Copy ke folder images di resources (atau simpan path absolut jika tidak ingin copy)
                    File destDir = new File("src/main/resources/images/");
                    if (!destDir.exists()) destDir.mkdirs();
                    File destFile = new File(destDir, selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    imagePath[0] = "/images/" + selectedFile.getName(); // path relatif untuk ImageView
                    gambarLabel.setText(selectedFile.getName());
                } catch (Exception ex) {
                    AlertUtil.showInfo("Gagal upload gambar: " + ex.getMessage());
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(8);
        grid.add(new Label("Nama:"), 0, 0); grid.add(nameField, 1, 0);
        grid.add(new Label("Stok:"), 0, 1); grid.add(stokField, 1, 1);
        grid.add(new Label("Harga:"), 0, 2); grid.add(hargaField, 1, 2);
        grid.add(new Label("Kategori:"), 0, 3); grid.add(kategoriBox, 1, 3);
        grid.add(new Label("Deskripsi:"), 0, 4); grid.add(deskripsiField, 1, 4);
        grid.add(new Label("Gambar:"), 0, 5); grid.add(pilihGambarBtn, 1, 5);
        grid.add(gambarLabel, 1, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    return new Product(
                        "P-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                        nameField.getText(),
                        kategoriBox.getValue(),
                        "", // brand
                        deskripsiField.getText(),
                        Double.parseDouble(hargaField.getText()),
                        Integer.parseInt(stokField.getText()),
                        imagePath[0],
                        MainApp.currentUser.getId()
                    );
                } catch (Exception e) {
                    AlertUtil.showInfo("Input tidak valid!");
                }
            }
            return null;
        });

        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(product -> {
            productService.addProduct(product);
            loadProducts();
        });
    }

    @FXML
    private void showEditProductDialog() {
        int idx = productList.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            AlertUtil.showInfo("Pilih produk yang ingin diedit!");
            return;
        }
        Product selected = myProducts.get(idx);

        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Edit Produk");

        TextField stokField = new TextField(String.valueOf(selected.getStock()));
        TextField hargaField = new TextField(String.valueOf(selected.getPrice()));

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(8);
        grid.add(new Label("Stok:"), 0, 0); grid.add(stokField, 1, 0);
        grid.add(new Label("Harga:"), 0, 1); grid.add(hargaField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    int newStock = Integer.parseInt(stokField.getText());
                    double newPrice = Double.parseDouble(hargaField.getText());
                    
                    if (newStock < 0 || newPrice < 0) {
                        AlertUtil.showInfo("Stok dan harga tidak boleh negatif!");
                        return null;
                    }
                    
                    return new Product(
                        selected.getId(),
                        selected.getName(),
                        selected.getCategory(),
                        selected.getBrand(),
                        selected.getDescription(),
                        newPrice,
                        newStock,
                        selected.getImage(),
                        selected.getSellerId()
                    );
                } catch (NumberFormatException e) {
                    AlertUtil.showInfo("Input tidak valid! Masukkan angka yang benar.");
                    return null;
                }
            }
            return null;
        });

        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(product -> {
            // Cari index di list semua produk
            List<Product> allProducts = productService.getAllProducts();
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getId().equals(selected.getId())) {
                    productService.updateProduct(i, product);
                    loadProducts(); // Refresh tampilan
                    AlertUtil.showInfo("Produk berhasil diupdate!");
                    return;
                }
            }
        });
    }

    @FXML
    private void deleteProduct() {
        // Ambil produk yang terpilih dari ListView atau TableView
        int selectedIndex = productList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Ambil produk berdasarkan index
            Product productToDelete = myProducts.get(selectedIndex);
            // Hapus produk dari produk yang tersimpan
            productService.deleteProduct(productToDelete.getId());
            // Update tampilan list produk
            loadProducts(); // Memuat ulang produk setelah penghapusan
            AlertUtil.showInfo("Produk berhasil dihapus.");
        } else {
            AlertUtil.showInfo("Pilih produk yang ingin dihapus.");
        }
    }

    @FXML
    private void handleLogout() {
        // Set currentUser menjadi null untuk menghapus sesi login
        MainApp.currentUser = null;

        // Tampilkan halaman login kembali
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) logoutBtn.getScene().getWindow();  // Menutup jendela saat ini
            stage.setScene(new Scene(loader.load()));  // Set scene ke halaman login
            stage.setTitle("BEAUTRA Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper class for order table
    public static class OrderRow {
        private final String orderId, productName, status;
        private final int qty;
        private final double total;

        public OrderRow(String orderId, String productName, int qty, double total, String status) {
            this.orderId = orderId;
            this.productName = productName;
            this.qty = qty;
            this.total = total;
            this.status = status;
        }
        public String getOrderId() { return orderId; }
        public String getProductName() { return productName; }
        public int getQty() { return qty; }
        public double getTotal() { return total; }
        public String getStatus() { return status; }
    }
}
