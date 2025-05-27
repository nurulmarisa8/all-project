package beautra.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckoutController {
    @FXML
    private TextField addressField;

    @FXML
    private TextField paymentMethodField;

    public void initialize() {
        // Inisialisasi data pembayaran dan alamat
    }

    @FXML
    private void handlePlaceOrder() {
        // Logika proses checkout dan simpan order
    }
}
