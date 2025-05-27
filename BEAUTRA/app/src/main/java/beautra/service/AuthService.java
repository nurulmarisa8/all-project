package beautra.service;

import beautra.model.abstracts.User;
import beautra.model.Buyer;
import beautra.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private User loggedInUser;

    public boolean login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Buat instance User sesuai role (contoh Buyer)
                Buyer buyer = new Buyer();
                buyer.setId(rs.getInt("id"));
                buyer.setFullname(rs.getString("fullname"));
                buyer.setEmail(email);
                buyer.setPassword(password);
                buyer.setPhone(rs.getString("phone"));
                buyer.setGender(rs.getString("gender"));
                buyer.setAddress(rs.getString("address"));
                loggedInUser = buyer;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerBuyer(Buyer buyer) {
        String sql = "INSERT INTO users (fullname, email, phone, password, gender, address, role) VALUES (?, ?, ?, ?, ?, ?, 'buyer')";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, buyer.getFullname());
            stmt.setString(2, buyer.getEmail());
            stmt.setString(3, buyer.getPhone());
            stmt.setString(4, buyer.getPassword());
            stmt.setString(5, buyer.getGender());
            stmt.setString(6, buyer.getAddress());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
