package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@Data
@NodeEntity
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private Double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentTime;
} 