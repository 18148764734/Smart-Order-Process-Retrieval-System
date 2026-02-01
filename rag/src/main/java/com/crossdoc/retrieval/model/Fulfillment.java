package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@Data
@NodeEntity
public class Fulfillment {
    @Id
    private String fulfillmentId;
    private String orderId;
    private String status;
    private LocalDateTime fulfillmentTime;
} 