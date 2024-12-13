package com.ecommerce.order.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderPlacedEvent {
//    @JsonProperty
//    private String email;
//    @JsonProperty
//    private String orderNumber;
//

    private final String orderNumber;
    private final String email;

    public OrderPlacedEvent(String orderNumber, String email) {
        this.orderNumber = orderNumber;
        this.email = email;
    }
}
