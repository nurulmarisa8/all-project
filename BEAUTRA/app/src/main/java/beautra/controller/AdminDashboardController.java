package beautra.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class AdminDashboardController {
    @FXML
    private TableView<?> usersTable;

    @FXML
    private TableView<?> productsTable;

    public void initialize() {
        // Inisialisasi data user & produk dari service/DAO ke tabel
    }

    @FXML
    private void handleDeleteUser() {
        // Logic hapus user terpilih
    }

    @FXML
    private void handleDeleteProduct() {
        // Logic hapus produk terpilih
    }
}
