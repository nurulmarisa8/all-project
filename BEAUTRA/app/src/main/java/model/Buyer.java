package model;

public class Buyer extends User {
    public Buyer(String id, String fullname, String email, String phone, String password, String gender, String address) {
        super(id, fullname, email, phone, password, gender, address, "buyer");
    }
}
