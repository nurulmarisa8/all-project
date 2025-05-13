package service;

import model.Mahasiswa;
import model.MataKuliah;
import java.util.*;

public class KRSManager implements IManageKRS {
    private Map<Mahasiswa, List<MataKuliah>> coursePlanMap = new HashMap<>();
    private List<MataKuliah> allCourses;

    public KRSManager() {
        allCourses = new ArrayList<>();
        allCourses.add(new MataKuliah("IF101", "Pemrograman Dasar", 3));
        allCourses.add(new MataKuliah("IF102", "Struktur Data", 4));
        allCourses.add(new MataKuliah("IF103", "Basis Data", 3));
    }

    public List<MataKuliah> getAllCourses() {
        return allCourses;
    }

    @Override
    public void addCourse(Mahasiswa student, MataKuliah course) {
        List<MataKuliah> list = coursePlanMap.getOrDefault(student, new ArrayList<>());
        if (list.contains(course)) {
            System.out.println("Mata kuliah sudah diambil.");
        } else {
            list.add(course);
            coursePlanMap.put(student, list);
            student.addCourse(course);
            System.out.println("Mata kuliah berhasil ditambahkan.");
        }
    }
}
