package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Product;

public class ProductDetailController {

    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private Label descriptionLabel;

    public void setProduct(Product product) {
        nameLabel.setText(product.getName());
        priceLabel.setText("Rp. " + product.getPrice());
        descriptionLabel.setText(product.getDescription());
    }
}
