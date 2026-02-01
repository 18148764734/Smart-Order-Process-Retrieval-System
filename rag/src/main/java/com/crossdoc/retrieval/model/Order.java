package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NodeEntity
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private LocalDateTime orderTime;
    private String status;
    private Double totalAmount;
    
    @Relationship(type = "HAS_PAYMENT")
    private Payment payment;
    
    @Relationship(type = "HAS_INVENTORY")
    private Inventory inventory;
    
    @Relationship(type = "HAS_FULFILLMENT")
    private Fulfillment fulfillment;
    
    @Relationship(type = "HAS_DELIVERY")
    private Delivery delivery;
    
    @Relationship(type = "CONTAINS")
    private List<OrderItem> items = new ArrayList<>();
} 