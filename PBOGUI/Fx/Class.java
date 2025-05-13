// Kelas abstrak
abstract class OverRiddingMahasiswa {
    protected String nama;
    protected String nim;

    // Constructor
    public OverRiddingMahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Metode abstrak yang harus diimplementasikan oleh kelas turunan
    public abstract void belajar();
    public abstract void tugas();

    // Metode umum
    public void perkenalan() {
        System.out.println("Halo, saya " + nama + " dengan NIM " + nim);
    }
}

// Subclass 1: Mahasiswa Reguler
class MahasiswaReguler extends OverRiddingMahasiswa {
    public MahasiswaReguler(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public void belajar() {
        System.out.println(nama + " sedang belajar di kampus.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas kuliah biasa.");
    }
}

// Subclass 2: Mahasiswa Transfer
class MahasiswaTransfer extends OverRiddingMahasiswa {
    private String universitasAsal;

    public MahasiswaTransfer(String nama, String nim, String universitasAsal) {
        super(nama, nim);
        this.universitasAsal = universitasAsal;
    }

    @Override
    public void belajar() {
        System.out.println(nama + " belajar sambil menyesuaikan diri dari transfer.");
    }

    @Override
    public void tugas() {
        System.out.println(nama + " mengerjakan tugas tambahan untuk adaptasi.");
    }

    public void infoTransfer() {
        System.out.println(nama + " berasal dari universitas " + universitasAsal + ".");
    }
}
