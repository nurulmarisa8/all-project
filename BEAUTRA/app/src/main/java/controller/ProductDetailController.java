package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Product;

import java.io.InputStream;

public class ProductDetailController {

    @FXML private ImageView productImageView;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private Label categoryLabel;
    @FXML private Label stockLabel;
    @FXML private Label descriptionLabel;

    /**
     * Method ini akan dipanggil dari HomeController untuk mengisi data ke halaman detail.
     * @param product Produk yang akan ditampilkan.
     */
    public void setProductDetails(Product product) {
        if (product == null) return;

        // Set teks untuk semua label
        nameLabel.setText(product.getName());
        priceLabel.setText("Rp " + String.format("%,.0f", product.getPrice()));
        categoryLabel.setText("Kategori: " + product.getCategory());
        stockLabel.setText("Stok: " + product.getStock());
        descriptionLabel.setText(product.getDescription());

        // Logika untuk memuat gambar (sama seperti di HomeController)
        try {
            String imagePath = product.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                if (!imagePath.startsWith("/")) {
                    imagePath = "/" + imagePath;
                }
                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    productImageView.setImage(new Image(imageStream));
                } else {
                    // Fallback jika gambar spesifik tidak ditemukan
                    loadDefaultImage();
                }
            } else {
                // Fallback jika path gambar kosong
                loadDefaultImage();
            }
        } catch (Exception e) {
            System.err.println("Gagal memuat gambar detail: " + e.getMessage());
            loadDefaultImage();
        }
    }

    /**
     * Helper method untuk memuat gambar default jika terjadi error.
     */
    private void loadDefaultImage() {
        try {
            InputStream defaultImageStream = getClass().getResourceAsStream("/images/default-product.png");
            if (defaultImageStream != null) {
                productImageView.setImage(new Image(defaultImageStream));
            }
        } catch (Exception ex) {
            // Jika default image pun gagal, kosongkan saja
            productImageView.setImage(null);
        }
    }
}