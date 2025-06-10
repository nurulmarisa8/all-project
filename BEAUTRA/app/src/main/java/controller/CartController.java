package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CartItem;
import model.Product;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class CartController {
    @FXML private VBox cartBox;
    @FXML private Label cartTotalLabel;
    @FXML private Label cartTotalValue;
    @FXML private Button checkoutBtn;

    private final List<CartItem> cart = new ArrayList<>();
    private final ProductService productService = new ProductService();

    public void setCart(List<CartItem> cart2) {
        cart.clear();
        for (CartItem ci : cart2) {
            cart.add(new CartItem(ci.getProductId(), ci.getQuantity(), ci.getPrice()));
        }
        updateCartBox();
    }

    private void updateCartBox() {
        cartBox.getChildren().clear();
        double total = 0;
        for (CartItem ci : cart) {
            Product p = productService.getAllProducts().stream()
                .filter(prod -> prod.getId().equals(ci.getProductId()))
                .findFirst().orElse(null);

            HBox row = new HBox(18);
            row.setStyle("-fx-background-color: white; -fx-background-radius: 22; -fx-padding: 18;");

            // Gambar produk
            ImageView img = new ImageView();
            img.setFitWidth(90);
            img.setFitHeight(90);
            img.setPreserveRatio(true);
            if (p != null && p.getImage() != null && !p.getImage().isEmpty()) {
                img.setImage(new Image(p.getImage()));
            } else {
                java.net.URL noImgUrl = getClass().getResource("/images/no-image.png");
                if (noImgUrl != null) {
                    img.setImage(new Image(noImgUrl.toExternalForm()));
                } else {
                    img.setStyle("-fx-background-color: #eeeeee; -fx-background-radius: 18;");
                }
            }

            // Info produk
            VBox info = new VBox(4);
            Label name = new Label(p != null ? p.getName() : ci.getProductId());
            name.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ff6666;");
            Label price = new Label("Rp" + String.format("%,.0f", ci.getPrice()));
            price.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            info.getChildren().addAll(name, price);

            // Kontrol jumlah
            HBox qtyBox = new HBox(6);
            Button minus = new Button("-");
            Label qty = new Label(String.valueOf(ci.getQuantity()));
            qty.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            Button plus = new Button("+");
            minus.setStyle("-fx-font-size: 18px; -fx-background-radius: 8;");
            plus.setStyle("-fx-font-size: 18px; -fx-background-radius: 8;");

            minus.setOnAction(e -> {
                if (ci.getQuantity() > 1) {
                    ci.setQuantity(ci.getQuantity() - 1);
                } else {
                    cart.remove(ci);
                }
                updateCartBox();
            });
            plus.setOnAction(e -> {
                ci.setQuantity(ci.getQuantity() + 1);
                updateCartBox();
            });
            qtyBox.getChildren().addAll(minus, qty, plus);

            row.getChildren().addAll(img, info, qtyBox);
            cartBox.getChildren().add(row);

            total += ci.getPrice() * ci.getQuantity();
        }
        cartTotalValue.setText("Rp" + String.format("%,.0f", total));
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public Button getCheckoutBtn() {
        return checkoutBtn;
    }
}
