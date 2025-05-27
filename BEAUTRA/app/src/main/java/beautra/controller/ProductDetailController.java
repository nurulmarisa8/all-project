package beautra.controller;

import beautra.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductDetailController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private ImageView productImage;

    private Product currentProduct;

    public void setProduct(Product product) {
        this.currentProduct = product;
        updateView();
    }

    private void updateView() {
        if (currentProduct != null) {
            nameLabel.setText(currentProduct.getName());
            descriptionLabel.setText(currentProduct.getDescription());
            priceLabel.setText(String.valueOf(currentProduct.getPrice()));
            // Load gambar ke productImage sesuai imagePath
        }
    }
}
