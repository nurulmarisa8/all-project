# 💄 Beautra — Beauty Aura E-Commerce JavaFX

**Beautra** (singkatan dari **Beauty Aura**) adalah aplikasi desktop e-commerce berbasis JavaFX yang menyediakan platform modern bagi pengguna untuk membeli dan menjual produk kecantikan. Aplikasi ini memiliki dua peran utama, yaitu **Pembeli (Buyer)** dan **Penjual (Seller)**, dengan tampilan serta fitur yang disesuaikan untuk masing-masing peran.

---

## 🚀 Fitur Utama

### 🔐 Fitur Umum
- **Login & Registrasi** — Pengguna bisa membuat akun, login, serta logout. Setelah login, aplikasi otomatis mengarahkan user ke halaman pembeli atau penjual sesuai peran.
- **Lupa Password** — Membantu pengguna mengatur ulang kata sandi jika lupa.
- **Routing Dinamis** — Pengalaman pengguna berbeda antara pembeli dan penjual.

### 🛍️ Fitur Pembeli (Buyer)
- **Dashboard Produk** — Menampilkan semua produk dalam format grid yang menarik.
- **Pencarian & Filter** — Cari produk berdasarkan nama atau kategori (SkinCare, BodyCare, Hair Care, Make Up).
- **Detail Produk** — Melihat info detail produk: gambar, nama, harga, stok, deskripsi.
- **Keranjang Belanja** — Tambahkan produk ke keranjang, ubah jumlah, hapus, serta validasi stok otomatis.
- **Checkout** — Isi data pengiriman, pilih metode pembayaran, cek ringkasan pesanan, lalu buat pesanan (stok otomatis berkurang).
- **Profil** — Melihat dan mengedit detail akun (nama, email, dll).

### 🧾 Fitur Penjual (Seller)
- **Dashboard Penjual** — Melihat daftar produk yang dijual dan pesanan yang masuk.
- **Manajemen Produk** — Tambah, edit, atau hapus produk. Validasi input otomatis.
- **Manajemen Pesanan** — Melihat detail pesanan, status, jumlah, dan data pembeli.

### 🛠️ Utilities & Service
- **AlertUtil** — Utility untuk menampilkan pop-up alert (informasi, error, konfirmasi) secara konsisten di seluruh aplikasi.
- **JsonUtil** — Utility untuk memudahkan baca/tulis data ke file JSON (`users.json`, `products.json`, `orders.json`).
- **ProductService & OrderService** — Pengelolaan data produk & order, CRUD, filtering, dsb.

---

## ⚙️ Cara Menjalankan Aplikasi

### 1. Prasyarat
- **Java Development Kit (JDK) 11+**
- **JavaFX** sudah terkonfigurasi di IDE
- Library eksternal: [`Gson`](https://github.com/google/gson)

### 2. Struktur Direktori
```
src
└── main
    ├── java
    │   ├── beautra
    │   │   └── MainApp.java
    │   ├── controller        # Logic tampilan & aksi (Login, Dashboard, Produk, dsb.)
    │   ├── model             # Data model (User, Product, Order)
    │   ├── service           # Service untuk produk & order
    │   └── util              # AlertUtil, JsonUtil, dsb.
    └── resources
        ├── css/
        ├── data/             # users.json, products.json, orders.json
        ├── fxml/             # UI layout JavaFX
        └── images/
```

### 3. Menjalankan
- Buka project di IDE (IntelliJ/Eclipse/VS Code, dll)
- Jalankan file `MainApp.java`
- Ikuti instruksi di layar untuk login atau registrasi

---

## 🧪 Pengujian Fitur

| No | Skenario Pengujian | Hasil Diharapkan | Status |
|----|--------------------|------------------|--------|
| **A. Login & Register** ||| |
| 1 | Login pembeli (`123`/`123`) | Masuk dashboard pembeli | ✅ |
| 2 | Login penjual (`456`/`123`) | Masuk dashboard penjual | ✅ |
| 3 | Login salah | Tampil error, tidak masuk | ✅ |
| 4 | Login kosong | Tidak ada aksi | ✅ |
| 5 | Registrasi valid | Akun tersimpan & redirect login | ✅ |
| **B. Buyer** ||| |
| 6 | Cari produk | Grid hanya tampil hasil pencarian | ✅ |
| 7 | Filter produk | Grid sesuai kategori | ✅ |
| 8 | Tambah ke keranjang | Kontrol jumlah tampil | ✅ |
| 9 | Edit/hapus keranjang | Update sesuai aksi | ✅ |
| 10 | Checkout data kosong | Peringatan input wajib | ✅ |
| 11 | Checkout valid | Order terekam & stok berkurang | ✅ |
| **C. Seller** ||| |
| 12 | Tambah produk valid | Produk baru muncul | ✅ |
| 13 | Tambah produk kosong | Validasi error tampil | ✅ |
| 14 | Edit produk | Update produk sukses | ✅ |
| 15 | Hapus produk | Konfirmasi & hapus produk | ✅ |
| 16 | Lihat pesanan | Tabel pesanan tampil | ✅ |
| 17 | Logout | Kembali ke login | ✅ |

---

## 🧑‍💻 Kontribusi & Pengembangan

Kontribusi sangat terbuka! Silakan pull request jika ingin menambah fitur, memperbaiki bug, atau memperbaiki UI.

---

## 📄 Lisensi

Proyek ini dikembangkan untuk keperluan edukasi. Bebas digunakan dan dimodifikasi selama untuk pembelajaran.

