package model;

public class Seller extends User {
    public Seller(String id, String fullname, String email, String phone, String password, String gender, String address) {
        super(id, fullname, email, phone, password, gender, address, "seller");
    }
}
