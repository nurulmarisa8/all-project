# 💄 Beautra — Aplikasi E-Commerce JavaFX

**Beautra** adalah aplikasi desktop e-commerce berbasis JavaFX yang menyediakan platform bagi pengguna untuk membeli dan menjual produk kecantikan. Aplikasi ini mendukung dua peran utama: **Pembeli (Buyer)** dan **Penjual (Seller)**, dengan fungsionalitas yang disesuaikan untuk masing-masing peran.

---

## 🚀 Fitur Utama

### 🔐 Fitur Umum
- **Autentikasi Pengguna**: Sistem login dan registrasi dengan routing berdasarkan peran.
- **Lupa Kata Sandi**: Fitur untuk menangani kasus kelupaan password.
- **Logout**: Keluar dari sesi dan kembali ke halaman login.

### 🛍️ Fitur Pembeli (Buyer)
- **Beranda Dinamis**: Tampilkan semua produk dalam layout grid interaktif.
- **Pencarian & Filter**: Cari berdasarkan nama atau kategori produk.
- **Detail Produk**: Informasi lengkap produk termasuk gambar, harga, dan deskripsi.
- **Keranjang Belanja**:
  - Tambah/hapus produk.
  - Ubah kuantitas dan validasi stok.
- **Checkout**:
  - Isi detail pengiriman & pilih metode pembayaran.
  - Update otomatis stok produk.
- **Profil Pengguna**: Lihat detail akun seperti nama, email, dan alamat.

### 🧾 Fitur Penjual (Seller)
- **Dasbor Penjual**: Kelola produk dan lihat daftar pesanan.
- **Manajemen Produk**:
  - Tambah, edit, dan hapus produk.
  - Validasi input pada setiap aksi.
- **Manajemen Pesanan**:
  - Lihat dan pantau pesanan produk yang dijual.

---

## ⚙️ Cara Menjalankan Aplikasi

### 1. ✅ Prasyarat
- Java Development Kit (JDK) versi **11+**
- JavaFX terinstal dan terkonfigurasi di IDE
- Library eksternal:
  - [`Gson`](https://github.com/google/gson) untuk pemrosesan JSON

### 2. 🗂️ Struktur Direktori
```
src
└── main
    ├── java
    │   ├── beautra
    │   │   └── MainApp.java
    │   ├── controller
    │   ├── model
    │   ├── service
    │   └── util
    └── resources
        ├── css/
        ├── data/
        │   ├── users.json
        │   ├── products.json
        │   └── orders.json
        ├── fxml/
        └── images/
```

### 3. ▶️ Menjalankan
- Buka proyek di IDE (IntelliJ, Eclipse, VS Code, dll.)
- Jalankan file `MainApp.java`
- Aplikasi akan menampilkan halaman login saat dimulai.

---

## 🧪 Tabel Pengujian Fitur

| No | Skenario Pengujian | Hasil Diharapkan | Status |
|----|---------------------|------------------|--------|
| **A. Login & Registrasi** ||| |
| 1  | Login dengan akun pembeli | Masuk ke beranda pembeli | ✅ |
| 2  | Login dengan akun penjual | Masuk ke dasbor penjual | ✅ |
| 3  | Email atau password salah | Tidak login, tampilkan error | ✅ |
| 4  | Form login kosong | Tidak berpindah halaman | ✅ |
| 5  | Navigasi ke registrasi | Tampilkan form registrasi | ✅ |
| 6  | Registrasi akun baru valid | Akun tersimpan & redirect ke login | ✅ |
| **B. Fitur Pembeli** ||| |
| 7  | Cari "Bath" | Tampilkan produk terkait | ✅ |
| 8  | Filter kategori "BodyCare" | Tampilkan produk sesuai kategori | ✅ |
| 9  | Tambah ke keranjang | Tampilkan kontrol kuantitas | ✅ |
| 10 | Buka keranjang | Tampilkan dialog keranjang | ✅ |
| 11 | Tambah kuantitas | Kuantitas bertambah | ✅ |
| 12 | Kurangi kuantitas | Kuantitas berkurang | ✅ |
| 13 | Kurangi sampai nol | Item terhapus dari keranjang | ✅ |
| 14 | Checkout tanpa isi form | Tampilkan peringatan input kosong | ✅ |
| 15 | Checkout lengkap | Pesanan dibuat & stok berkurang | ✅ |
| 16 | Klik detail produk | Tampilkan popup informasi lengkap | ✅ |
| **C. Fitur Penjual** ||| |
| 17 | Tambah produk kosong | Validasi gagal & tampilkan pesan | ✅ |
| 18 | Tambah produk valid | Produk muncul & tersimpan | ✅ |
| 19 | Edit produk | Perubahan tersimpan & tampil | ✅ |
| 20 | Hapus produk | Konfirmasi & hapus produk | ✅ |
| 21 | Lihat pesanan masuk | Tampilkan pesanan terkait produk | ✅ |
| 22 | Logout | Kembali ke halaman login | ✅ |

---

## 🧰 Utilitas Pendukung
- **`AlertUtil`**: Menyediakan tampilan alert standar untuk notifikasi, error, dan konfirmasi.
- **`JsonUtil`**: Utility class untuk membaca dan menulis data JSON (produk, pengguna, pesanan) menggunakan `Gson`.

---

## 🧑‍💻 Kontribusi
Kontribusi terbuka untuk perbaikan bug, penambahan fitur, atau peningkatan performa UI. Silakan lakukan pull request ke repository utama.

---

## 📄 Lisensi
Aplikasi ini dikembangkan sebagai proyek pembelajaran. Bebas digunakan dan dimodifikasi untuk keperluan edukasi.

