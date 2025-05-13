public class MainOverRidding {
    public static void main(String[] args) {
        // Membuat objek dari kelas turunan
        OverRiddingMahasiswa reguler = new MahasiswaReguler( "Aco", "12345");
        OverRiddingMahasiswa transfer = new MahasiswaTransfer( "Baddu",  "67890", "UNHAS");

        // Memanggil metode menggunakan referensi Mahasiswa
        reguler.perkenalan();
        reguler.belajar();
        reguler.tugas();
        System.out.println(); // Baris kosong untuk memisahkan output
        transfer.perkenalan();
        transfer.belajar();
        transfer.tugas();

        // Menggunakan fitur spesifik dari MahasiswaTransfer
        if (transfer instanceof MahasiswaTransfer) {
            ((MahasiswaTransfer) transfer).infoTransfer();
        }
    }
}