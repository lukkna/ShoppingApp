package eu.shopping.app.usecase.api.entity;

public class BoundaryShoppingRecord {
    private final String item;
    private final Double price;
    private final String buyer;
    private final Long id;

    public BoundaryShoppingRecord(String item, Double price, String buyer, Long id) {
        this.item = item;
        this.price = price;
        this.buyer = buyer;
        this.id = id;
    }

    public BoundaryShoppingRecord(String item, Double price, String buyer) {
        this.item = item;
        this.price = price;
        this.buyer = buyer;
        id = null;
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

    public Long getId() {
        return id;
    }
}