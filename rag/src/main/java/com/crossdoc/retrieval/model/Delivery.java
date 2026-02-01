package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@Data
@NodeEntity
public class Delivery {
    @Id
    private String deliveryId;
    private String orderId;
    private String carrierId;
    private String trackingNumber;
    private String status;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
} 