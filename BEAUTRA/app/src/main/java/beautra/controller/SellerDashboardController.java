package beautra.controller;

import beautra.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SellerDashboardController {
    @FXML
    private ListView<Product> productListView;

    public void initialize() {
        // Load produk milik seller ke productListView
    }

    @FXML
    private void handleAddProduct() {
        // Logic tambah produk baru
    }

    @FXML
    private void handleEditProduct() {
        // Logic edit produk terpilih
    }
}
