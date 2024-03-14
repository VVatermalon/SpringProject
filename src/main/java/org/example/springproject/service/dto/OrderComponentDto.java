package org.example.springproject.service.dto;

public class OrderComponentDto {
    private OrderSimpleDto order;
    private SushiSimpleDto sushi;
    private int amount;
    public OrderSimpleDto getOrder() {
        return order;
    }

    public void setOrder(OrderSimpleDto order) {
        this.order = order;
    }

    public SushiSimpleDto getSushi() {
        return sushi;
    }

    public void setSushi(SushiSimpleDto sushi) {
        this.sushi = sushi;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
