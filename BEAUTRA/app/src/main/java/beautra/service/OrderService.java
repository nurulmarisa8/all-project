package beautra.service;

import beautra.model.Order;
import beautra.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

    public boolean createOrder(Order order) {
        String sql = "INSERT INTO orders (buyer_id, total_price, status, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getBuyerId());
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setString(3, order.getStatus());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metode lain untuk update status, ambil riwayat dll
}
