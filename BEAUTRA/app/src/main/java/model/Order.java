package model;

import java.util.List;

public class Order implements ITransaksi {
    private String id;
    private List<CartItem> items;
    private double total;
    private String status;
    private String timestamp;

    public Order(String id, String buyerId, List<CartItem> items, String note, String voucher, String shipping, String paymentMethod, double total, String status, String timestamp) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.status = status;
        this.timestamp = timestamp;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<CartItem> getItems() {
        return items;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

}
