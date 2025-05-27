package beautra.controller;

import beautra.model.CartItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CartController {
    @FXML
    private ListView<CartItem> cartListView;

    public void initialize() {
        // Load data CartItem ke ListView
    }

    @FXML
    private void handleRemoveItem() {
        // Hapus item dari keranjang
    }

    @FXML
    private void handleCheckout() {
        // Navigasi ke checkout page
    }
}
