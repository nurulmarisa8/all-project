package controller;

import beautra.MainApp; // Pastikan ada MainApp.currentUser
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import model.Product;
import model.CartItem;
import service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class HomeController {
    @FXML private ListView<String> productList;
    @FXML private TextField searchField;
    @FXML private FlowPane productGrid;
    @FXML private Button forYouBtn, skinCareBtn, bodyCareBtn, hairCareBtn, makeUpBtn;
    @FXML private Button cartIconBtn;
    @FXML private Button logoutBtn; 

    private final ProductService productService = new ProductService();
    private List<Product> allProducts;

    private final List<CartItem> cart = new ArrayList<>();

    private Stage checkoutStage;
    private CheckoutSlideController checkoutSlideController;

    @FXML
    public void initialize() {
        allProducts = productService.getAllProducts();
        showProducts(allProducts);
    }

    private void showProducts(List<Product> products) {
        productGrid.getChildren().clear();
        
        for (Product product : products) {
            VBox productCard = createProductCard(product);
            productGrid.getChildren().add(productCard);
        }
    }

    private VBox createProductCard(Product product) {
        VBox card = new VBox(8);
        card.setPrefWidth(180);
        card.setPrefHeight(250);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-padding: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);");
        
        // Image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        
        try {
            // Pastikan path gambar dimulai dengan "/"
            String imagePath = product.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                // Jika path tidak dimulai dengan "/", tambahkan "/"
                if (!imagePath.startsWith("/")) {
                    imagePath = "/" + imagePath;
                }
                
                // Load image dari resources
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    Image image = new Image(imageStream);
                    imageView.setImage(image);
                } else {
                    // Fallback ke default image jika gambar tidak ditemukan
                    Image defaultImage = new Image(getClass().getResourceAsStream("/images/default-product.png"));
                    imageView.setImage(defaultImage);
                }
            } else {
                // Default image jika path kosong
                Image defaultImage = new Image(getClass().getResourceAsStream("/images/default-product.png"));
                imageView.setImage(defaultImage);
            }
        } catch (Exception e) {
            System.err.println("Error loading image for product: " + product.getName() + " - " + e.getMessage());
            // Set default image on error
            try {
                Image defaultImage = new Image(getClass().getResourceAsStream("/images/default-product.png"));
                imageView.setImage(defaultImage);
            } catch (Exception ex) {
                // Jika default image juga tidak ada, buat placeholder
                imageView.setStyle("-fx-background-color: #f0f0f0;");
            }
        }
        
        // Product info
        Label nameLabel = new Label(product.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        nameLabel.setWrapText(true);
        nameLabel.setMaxWidth(150);
        
        Label priceLabel = new Label("Rp " + String.format("%.0f", product.getPrice()));
        priceLabel.setStyle("-fx-text-fill: #ff6666; -fx-font-weight: bold; -fx-font-size: 13px;");
        
        Label stockLabel = new Label("Stok: " + product.getStock());
        stockLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 11px;");
        
        Button addToCartBtn = new Button("+ Keranjang");
        addToCartBtn.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white; -fx-background-radius: 6; -fx-font-size: 11px;");
        addToCartBtn.setOnAction(e -> addToCart(product));
        
        card.getChildren().addAll(imageView, nameLabel, priceLabel, stockLabel, addToCartBtn);
        return card;
    }


    @FXML
    private void onSearchButtonClick() {
        String searchText = searchField.getText();
        searchProducts(searchText);
    }

    private void searchProducts(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            showProducts(allProducts);
            return;
        }
        
        String searchLower = searchText.toLowerCase().trim();
        List<Product> filteredProducts = allProducts.stream()
            .filter(p -> 
                p.getName().toLowerCase().contains(searchLower) ||
                p.getBrand().toLowerCase().contains(searchLower) ||
                p.getCategory().toLowerCase().contains(searchLower) ||
                p.getDescription().toLowerCase().contains(searchLower)
            )
            .collect(Collectors.toList());
        
        showProducts(filteredProducts);
    }

    @FXML
    private void goToBestSeller() {        // Navigasi ke halaman best seller (opsional)
    }

    // Menambahkan produk baru ke dalam daftar
    private void addProduct(Product product) { 
        productService.addProduct(product);
        allProducts.add(product);
        showProducts(allProducts);
    }

    // Menghapus produk dari daftar
    private void removeProduct(Product product) {
        productService.addProduct(product);
        allProducts.remove(product);
        showProducts(allProducts);
    }

    // Mengupdate informasi produk
    private void updateProduct(Product product) {
        productService.updateProduct(0, product);
        int index = allProducts.indexOf(product);
        if (index >= 0) {
            allProducts.set(index, product);
            showProducts(allProducts);
        }
    }

    @FXML
    private void onForYou() {
        setActiveCategory(forYouBtn);
        showProducts(allProducts); // Tampilkan semua produk
    }

    @FXML
    private void onSkinCare() {
        setActiveCategory(skinCareBtn);
        showProducts(allProducts.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase("SkinCare"))
            .collect(Collectors.toList()));
    }

    @FXML
    private void onBodyCare() {
        setActiveCategory(bodyCareBtn);
        showProducts(allProducts.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase("BodyCare"))
            .collect(Collectors.toList()));
    }

    @FXML
    private void onHairCare() {
        setActiveCategory(hairCareBtn);
        showProducts(allProducts.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase("Hair Care"))
            .collect(Collectors.toList()));
    }

    @FXML
    private void onMakeUp() {
        setActiveCategory(makeUpBtn);
        showProducts(allProducts.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase("Make Up"))
            .collect(Collectors.toList()));
    }

    private void setActiveCategory(Button activeBtn) {
        forYouBtn.getStyleClass().setAll("soft-btn");
        skinCareBtn.getStyleClass().setAll("soft-btn");
        bodyCareBtn.getStyleClass().setAll("soft-btn");
        hairCareBtn.getStyleClass().setAll("soft-btn");
        makeUpBtn.getStyleClass().setAll("soft-btn");
        activeBtn.getStyleClass().setAll("category-btn-active");
    }

    private void showCheckoutSlide(Product product) {
        try {
            if (checkoutStage == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkout_slide.fxml"));
                Parent root = loader.load();
                checkoutSlideController = loader.getController();

                checkoutStage = new Stage();
                checkoutStage.initStyle(StageStyle.UNDECORATED);
                checkoutStage.initModality(Modality.APPLICATION_MODAL);
                checkoutStage.setScene(new Scene(root));
                checkoutStage.setWidth(400);
                checkoutStage.setHeight(690);
                checkoutStage.setX(productGrid.getScene().getWindow().getX() + productGrid.getScene().getWindow().getWidth() - 400);
                checkoutStage.setY(productGrid.getScene().getWindow().getY());
            }
            checkoutSlideController.addToCart(product);
            checkoutStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCheckout(List<Product> cart) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkout.fxml"));
            Parent root = loader.load();
            CheckoutController checkoutController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Checkout");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ubah addToCart agar hanya menambah ke list cart (tanpa update ListView di home)
    private void addToCart(Product product) {
        CartItem existing = cart.stream()
            .filter(ci -> ci.getProductId().equals(product.getId()))
            .findFirst().orElse(null);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            cart.add(new CartItem(product.getId(), 1, product.getPrice()));
        }
    }

    @FXML
    private void openCart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
            Parent root = loader.load();
            CartController cartController = loader.getController();
            cartController.setCart(cart); // Kirim List<CartItem>

            cartController.getCheckoutBtn().setOnAction(e -> {
                Stage cartStage = (Stage) cartController.getCheckoutBtn().getScene().getWindow();
                cartStage.close();
                try {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/checkout.fxml"));
                    Parent root1 = loader1.load();
                    CheckoutController checkoutController = loader1.getController();
                    checkoutController.checkout(cart, "buyerId"); // Kirim List<CartItem>
                    Stage stage = new Stage();
                    stage.setTitle("Checkout");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            Stage stage = new Stage();
            stage.setTitle("Keranjang Belanja");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profile.fxml"));
            Parent root = loader.load();
            ProfileController controller = loader.getController();
            controller.setUser(MainApp.currentUser); // Pastikan currentUser sudah di-set saat login
            Stage stage = new Stage();
            stage.setTitle("Profil Saya");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        // Hapus session pengguna
        MainApp.currentUser = null; // Logout user

        // Pindah ke halaman login
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) logoutBtn.getScene().getWindow(); // Dapatkan window dari tombol logout
            stage.setScene(new Scene(loader.load())); // Ganti scene ke halaman login
            stage.setTitle("BEAUTRA Login"); // Set title untuk halaman login
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void openDelivery() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/delivery.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Status Pengiriman");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
