````markdown
# Proyek Aplikasi E-Commerce "Beautra"

Beautra adalah aplikasi desktop e-commerce yang dibangun menggunakan JavaFX. Aplikasi ini menyediakan platform bagi pengguna untuk membeli dan menjual produk kecantikan. Aplikasi ini memiliki dua peran utama: Pembeli (Buyer) dan Penjual (Seller), masing-masing dengan fungsionalitas yang disesuaikan.

## Fitur-fitur Aplikasi

Aplikasi Beautra memiliki serangkaian fitur yang dirancang untuk memberikan pengalaman pengguna yang lengkap, baik untuk pembeli maupun penjual.

### Fitur Umum
- **Sistem Autentikasi**: Pengguna dapat mendaftar dan masuk ke dalam aplikasi. Sistem akan mengarahkan pengguna ke halaman yang sesuai berdasarkan peran mereka (pembeli atau penjual).
- **Lupa Kata Sandi**: Terdapat fungsionalitas untuk menangani kasus lupa kata sandi.
- **Logout**: Pengguna dapat keluar dari sesi mereka untuk kembali ke halaman login.

### Fitur untuk Pembeli (Buyer)
- **Halaman Utama**: Menampilkan semua produk yang tersedia dalam format grid yang menarik.
- **Pencarian Produk**: Pengguna dapat mencari produk berdasarkan nama atau kategori.
- **Filter Kategori**: Produk dapat difilter berdasarkan kategori seperti "SkinCare", "BodyCare", "Hair Care", dan "Make Up".
- **Detail Produk**: Dengan mengklik produk, pengguna dapat melihat halaman detail yang menampilkan informasi lengkap termasuk nama, gambar, harga, stok, dan deskripsi produk.
- **Manajemen Keranjang Belanja**:
    - Pengguna dapat menambahkan produk ke keranjang langsung dari halaman utama atau halaman detail.
    - Kuantitas produk di keranjang dapat ditambah atau dikurangi. Jika kuantitas menjadi nol, produk akan dihapus dari keranjang.
    - Aplikasi akan memberikan peringatan jika pengguna mencoba menambahkan produk melebihi stok yang tersedia.
- **Proses Checkout**:
    - Pengguna dapat melanjutkan ke halaman checkout dari keranjang belanja mereka.
    - Halaman checkout menampilkan ringkasan pesanan, total biaya, dan biaya pengiriman.
    - Pengguna harus mengisi informasi pengiriman (nama, telepon, alamat) dan memilih metode pembayaran.
    - Setelah pesanan berhasil dibuat, stok produk akan otomatis berkurang.
- **Profil Pengguna**: Pengguna dapat melihat detail profil mereka sendiri, termasuk nama lengkap, email, dan informasi pribadi lainnya.

### Fitur untuk Penjual (Seller)
- **Dasbor Penjual**: Halaman utama untuk penjual yang menampilkan daftar produk yang mereka jual dan pesanan yang masuk untuk produk mereka.
- **Manajemen Produk**:
    - **Tambah Produk**: Penjual dapat menambahkan produk baru melalui sebuah dialog form, lengkap dengan nama, kategori, deskripsi, harga, stok, dan gambar. Terdapat validasi untuk memastikan semua data terisi dengan benar.
    - **Edit Produk**: Penjual dapat mengubah detail produk yang sudah ada.
    - **Hapus Produk**: Penjual dapat menghapus produk dari daftar jual mereka setelah konfirmasi.
- **Manajemen Pesanan**: Penjual dapat melihat daftar pesanan yang masuk khusus untuk produk yang mereka jual, termasuk detail seperti ID pesanan, nama produk, kuantitas, total harga, dan status pesanan.

## Cara Menjalankan Aplikasi

1.  **Prasyarat**:
    * Pastikan Anda memiliki Java Development Kit (JDK) versi 11 atau yang lebih baru terinstal.
    * Proyek ini menggunakan JavaFX. Pastikan library JavaFX sudah terkonfigurasi di IDE Anda.
    * Proyek ini menggunakan library `Gson` untuk pemrosesan JSON. Pastikan library tersebut sudah ditambahkan ke dalam *build path* proyek.

2.  **Struktur Proyek**:
    Pastikan struktur direktori proyek Anda sesuai dengan yang diharapkan. File-file data JSON terletak di `src/main/resources/data/` dan file FXML (UI) berada di `src/main/resources/fxml/`.

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
            ├── css
            ├── data
            │   ├── orders.json
            │   ├── products.json
            │   └── users.json
            ├── fxml
            └── images
    ```

3.  **Menjalankan Aplikasi**:
    * Buka proyek di IDE pilihan Anda (misalnya, IntelliJ IDEA, Eclipse, atau VS Code).
    * Temukan dan jalankan file `MainApp.java` yang terletak di dalam paket `beautra`.
    * Aplikasi akan dimulai dan menampilkan jendela login.

## Tabel Pengujian Aplikasi

Berikut adalah tabel yang merangkum berbagai skenario pengujian untuk memastikan semua fitur berfungsi sesuai harapan.

| No | Deskripsi Pengujian | Hasil yang Diharapkan | Hasil |
| :-- | :--- | :--- | :--- |
| **A** | **Modul Login & Registrasi** | | |
| 1 | Login dengan email dan password benar (`123`, `123`) | Berpindah ke menu utama pembeli. | Berhasil |
| 2 | Login dengan email dan password benar (`456`, `123`) | Berpindah ke dasbor penjual. | Berhasil |
| 3 | Login dengan email atau password salah | Sistem tidak berpindah halaman. | Berhasil |
| 4 | Login tanpa mengisi email atau password | Sistem tidak melakukan apa-apa. | Berhasil |
| 5 | Pindah ke halaman registrasi dari halaman login | Menampilkan form registrasi. | Berhasil |
| 6 | Mendaftar akun baru dengan data yang valid | Akun baru tersimpan di `users.json` dan pengguna diarahkan ke halaman login. | Berhasil |
| **B** | **Fitur Pembeli (Buyer)** | | |
| 7 | Mencari produk "Bath" di halaman utama | Grid produk hanya menampilkan "Bath and Body Works". | Berhasil |
| 8 | Memfilter produk berdasarkan kategori "BodyCare" | Grid produk menampilkan "Bath and Body Works" dan "Love Beauty & Planet". | Berhasil |
| 9 | Menambah produk ke keranjang dari halaman utama | Tombol "+ Keranjang" berubah menjadi kontrol kuantitas. | Berhasil |
| 10 | Membuka keranjang belanja | Dialog keranjang muncul menampilkan item yang ditambahkan. | Berhasil |
| 11 | Menambah kuantitas item di keranjang | Angka kuantitas pada item tersebut bertambah. | Berhasil |
| 12 | Mengurangi kuantitas item hingga 1 di keranjang | Angka kuantitas pada item tersebut berkurang. | Berhasil |
| 13 | Mengurangi kuantitas item saat kuantitas = 1 | Item terhapus dari keranjang. | Berhasil |
| 14 | Melakukan checkout dengan form yang belum diisi lengkap | Menampilkan pesan "Harap lengkapi semua data pengiriman!". | Berhasil |
| 15 | Melakukan checkout dengan data lengkap | Pesanan berhasil dibuat, `orders.json` terisi, stok di `products.json` berkurang, dan keranjang menjadi kosong. | Berhasil |
| 16 | Membuka detail produk dari halaman utama | Dialog detail produk muncul dengan informasi yang sesuai. | Berhasil |
| **C** | **Fitur Penjual (Seller)** | | |
| 17 | Menambah produk baru tanpa mengisi semua field | Menampilkan pesan "Semua kolom wajib diisi!" dan dialog tidak tertutup. | Berhasil |
| 18 | Menambah produk baru dengan data valid | Produk baru muncul di daftar produk penjual dan tersimpan di `products.json`. | Berhasil |
| 19 | Mengedit produk yang sudah ada | Perubahan tersimpan di `products.json` dan tampilan di daftar produk diperbarui. | Berhasil |
| 20 | Menghapus produk | Produk terhapus dari daftar setelah konfirmasi. | Berhasil |
| 21 | Melihat daftar pesanan masuk | Tabel pesanan menampilkan item-item yang dibeli dari penjual tersebut. | Berhasil |
| 22 | Logout dari akun penjual/pembeli | Aplikasi kembali ke halaman login. | Berhasil |

````
