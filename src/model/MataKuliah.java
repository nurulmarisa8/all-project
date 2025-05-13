package model;

public class MataKuliah {
    private String code;
    private String name;
    private int credits;

    public MataKuliah(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCourseInfo() {
        return code + " - " + name + " (" + credits + " SKS)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MataKuliah that = (MataKuliah) obj;
        return this.code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
