package beautra.controller;

import beautra.model.Buyer;
import beautra.service.AuthService;
import beautra.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {
    @FXML private TextField fullnameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private PasswordField passwordField;
    @FXML private ChoiceBox<String> genderChoiceBox;
    @FXML private TextArea addressField;

    private AuthService authService = new AuthService();

    @FXML
    private void handleRegister(ActionEvent event) {
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        String gender = genderChoiceBox.getValue();
        String address = addressField.getText();

        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            AlertUtil.showError("Form tidak lengkap", "Harap isi semua data penting.");
            return;
        }

        Buyer buyer = new Buyer();
        buyer.setFullname(fullname);
        buyer.setEmail(email);
        buyer.setPhone(phone);
        buyer.setPassword(password);
        buyer.setGender(gender);
        buyer.setAddress(address);

        if (authService.registerBuyer(buyer)) {
            AlertUtil.showInfo("Berhasil", "Registrasi berhasil. Silakan login.");
            // TODO: pindah ke halaman login
        } else {
            AlertUtil.showError("Gagal", "Registrasi gagal, coba lagi.");
        }
    }
}
