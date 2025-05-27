package beautra.model;

import java.util.Date;

public class Order {
    private int id;
    private int buyerId;
    private double totalPrice;
    private String status;
    private Date createdAt;
    public Order() {
    }

        public Order(int id, int buyerId, double totalPrice, String status, Date createdAt) {
            this.id = id;
            this.buyerId = buyerId;
            this.totalPrice = totalPrice;
            this.status = status;
            this.createdAt = createdAt;
        }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(int buyerId) {
            this.buyerId = buyerId;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }
}
