package service;

import model.Mahasiswa;
import model.MataKuliah;

public interface IManageKRS {
    void addCourse(Mahasiswa student, MataKuliah course);
}
