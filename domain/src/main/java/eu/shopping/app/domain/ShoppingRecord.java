package eu.shopping.app.domain;

public class ShoppingRecord {
    private final String item;
    private final Double price;
    private final String buyer;
    private final Long timestamp;

    public ShoppingRecord(String item, Double price, String buyer, Long timestamp) {
        this.item = item;
        this.price = price;
        this.buyer = buyer;
        this.timestamp = timestamp;
    }

    public ShoppingRecord(String item, Double price, String buyer) {
        this.item = item;
        this.price = price;
        this.buyer = buyer;
        timestamp = null;
    }

    public String getItem() {
        return item;
    }

    public Double getPrice() {
        return price;
    }

    public String getBuyer() {
        return buyer;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}