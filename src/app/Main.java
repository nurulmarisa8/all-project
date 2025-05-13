package app;

import model.*;
import service.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KRSManager krsManager = new KRSManager();

        Mahasiswa mhs = new Mahasiswa("user1", "pass123", "Budi", "12345678", "Informatika", 3);

        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        String code = LoginHelper.generateVerificationCode();
        System.out.println("Kode Verifikasi: " + code);
        System.out.print("Masukkan kode verifikasi: ");
        String inputCode = sc.nextLine();

        if (mhs.login(username, password, inputCode)) {
            System.out.println("Login berhasil!\n");

            boolean running = true;
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Lihat Profil");
                System.out.println("2. Lihat Daftar Mata Kuliah");
                System.out.println("3. Isi KRS (Tambah Mata Kuliah)");
                System.out.println("4. Lihat KRS yang Diambil");
                System.out.println("5. Logout");
                System.out.print("Pilih: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> mhs.viewProfile();
                    case 2 -> {
                        List<MataKuliah> daftarMK = krsManager.getAllCourses();
                        for (MataKuliah mk : daftarMK) {
                            System.out.println(mk.getCourseInfo());
                        }
                    }
                    case 3 -> {
                        List<MataKuliah> daftarMK = krsManager.getAllCourses();
                        for (int i = 0; i < daftarMK.size(); i++) {
                            System.out.println((i + 1) + ". " + daftarMK.get(i).getCourseInfo());
                        }
                        System.out.print("Pilih nomor mata kuliah: ");
                        int pilih = sc.nextInt();
                        sc.nextLine();
                        if (pilih >= 1 && pilih <= daftarMK.size()) {
                            krsManager.addCourse(mhs, daftarMK.get(pilih - 1));
                        }
                    }
                    case 4 -> mhs.viewStudyPlan();
                    case 5 -> {
                        mhs.logout();
                        running = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            }
        } else {
            System.out.println("Login gagal!");
        }
        sc.close();
    }
}
