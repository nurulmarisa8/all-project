package beautra.model;

public class CartItem {
    private int id;
    private int buyerId;
    private int productId;
    private int quantity;

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

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
}