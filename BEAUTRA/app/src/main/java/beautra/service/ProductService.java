package beautra.service;

import beautra.model.Product;
import beautra.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setSellerId(rs.getInt("seller_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setCategory(rs.getString("category"));
                p.setImagePath(rs.getString("image_path"));
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Metode lain untuk tambah, edit, hapus produk
}
