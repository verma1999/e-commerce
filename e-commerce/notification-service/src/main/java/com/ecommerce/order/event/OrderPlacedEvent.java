package com.ecommerce.order.event;

import lombok.*;

@Getter
@Setter
public class OrderPlacedEvent {

    private String orderNumber;
    private String email;

    public OrderPlacedEvent(String orderNumber, String email) {
        this.orderNumber = orderNumber;
        this.email = email;
    }

    public OrderPlacedEvent() {
    }

}
