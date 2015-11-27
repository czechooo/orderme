package com.ggralak.model;

public class OrderItem {
    String text;

    public OrderItem() {
    }

    public OrderItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
