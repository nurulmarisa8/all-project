package beautra.controller;

import com.beautra.service.AuthService;
import com.beautra.util.AlertUtil;
import com.beautra.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private AuthService authService = new AuthService();

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (authService.login(email, password)) {
            // Simpan session user
            Session.setCurrentUser(authService.getLoggedInUser());
            // TODO: pindah ke halaman home sesuai role
        } else {
            AlertUtil.showError("Login gagal", "Email atau password salah");
        }
    }
}
