package model;

import java.util.ArrayList;
import java.util.List;

public class Mahasiswa extends User {
    private String name;
    private String nim;
    private String major;
    private int semester;
    private List<MataKuliah> courseList = new ArrayList<>();

    public Mahasiswa(String username, String password, String name, String nim, String major, int semester) {
        super(username, password);
        this.name = name;
        this.nim = nim;
        this.major = major;
        this.semester = semester;
    }

    public boolean login(String username, String password, String code) {
        return this.username.equals(username) && this.password.equals(password) && code.equals("1234");
    }

    public void logout() {
        System.out.println("Logout berhasil!");
    }

    public void viewProfile() {
        System.out.println("Nama: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Jurusan: " + major);
        System.out.println("Semester: " + semester);
    }

    public void viewStudyPlan() {
        System.out.println("\nMata Kuliah yang Diambil:");
        for (MataKuliah mk : courseList) {
            System.out.println("- " + mk.getCourseInfo());
        }
    }

    public void addCourse(MataKuliah mk) {
        courseList.add(mk);
    }
}
