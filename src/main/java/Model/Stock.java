package Model;

import java.sql.Timestamp;

public class Stock {
    private int stockID;
    private int productID;
    private int userID;
    private int quantity;
    private String type; // "import" hoặc "export"
    private Timestamp timestamp;
    private double totalPrice;

    public Stock() {
    }

    public Stock(int stockID, int productID, int userID, int quantity, String type, Timestamp timestamp, double totalPrice) {
        this.stockID = stockID;
        this.productID = productID;
        this.userID = userID;
        this.quantity = quantity;
        this.type = type;
        this.timestamp = timestamp;
        this.totalPrice = totalPrice;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockID=" + stockID +
                ", productID=" + productID +
                ", userID=" + userID +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
