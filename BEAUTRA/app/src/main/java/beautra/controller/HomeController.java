package beautra.controller;

import beautra.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomeController {
    @FXML
    private ListView<Product> productListView;

    @FXML
    private TextField searchField;

    public void initialize() {
        // Load semua produk ke productListView
    }

    @FXML
    private void handleSearch() {
        // Cari produk berdasarkan searchField dan refresh productListView
    }
}
