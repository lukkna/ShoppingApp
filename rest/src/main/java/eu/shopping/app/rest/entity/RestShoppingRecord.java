package eu.shopping.app.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestShoppingRecord {
    private final String item;
    private final Double price;
    private final String buyer;
    private final Long id;

    public RestShoppingRecord(String item, Double price, String buyer, Long id) {
        this.item = item;
        this.price = price;
        this.buyer = buyer;
        this.id = id;
    }

    public RestShoppingRecord(@JsonProperty("item") String item,
                              @JsonProperty("price") Double price,
                              @JsonProperty("buyer") String buyer) {
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