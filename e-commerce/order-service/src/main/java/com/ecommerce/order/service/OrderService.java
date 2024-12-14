package com.ecommerce.order.service;

import com.ecommerce.order.client.InventoryClient;
import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.event.OrderPlacedEvent;
import com.ecommerce.order.exception.InsufficientStockException;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
            log.info(order.getOrderNumber());
            log.info("email ={}", orderRequest.userDetails().email());
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName() != null ? orderRequest.userDetails().firstName() : "Unknown");
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName() != null ? orderRequest.userDetails().lastName() : "Unknown");

//            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
//            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
            log.info("Start - sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);
        }
        else {
            throw new InsufficientStockException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
