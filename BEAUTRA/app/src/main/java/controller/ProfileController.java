package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.User;

public class ProfileController {
    @FXML private ImageView avatarView;
    @FXML private Label fullnameLabel, emailLabel, phoneLabel, genderLabel, addressLabel, roleLabel;

    public void setUser(User user) {
        fullnameLabel.setText(user.getFullname());
        emailLabel.setText("Email: " + user.getEmail());
        phoneLabel.setText("Phone: " + user.getPhone());
        genderLabel.setText("Gender: " + user.getGender());
        addressLabel.setText("Address: " + user.getAddress());
        roleLabel.setText("Role: " + user.getRole());
        // Set avatar jika ada, default user.png
        avatarView.setImage(new Image(getClass().getResource("/images/user.png").toExternalForm()));
    }
}