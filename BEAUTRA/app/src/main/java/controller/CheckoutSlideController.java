package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CheckoutSlideController {
    @FXML private Label totalLabel;
    @FXML private Button checkoutBtn;

    private final List<Product> cart = new ArrayList<>();

    public void addToCart(Product product) {
        cart.add(product);
        updateTotal();
    }

    private void updateTotal() {
        double total = cart.stream().mapToDouble(Product::getPrice).sum();
        totalLabel.setText("Total: Rp" + String.format("%,.0f", total));
    }

    public List<Product> getCart() {
        return cart;
    }
}