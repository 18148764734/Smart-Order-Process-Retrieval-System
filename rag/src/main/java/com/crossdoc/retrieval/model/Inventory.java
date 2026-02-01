package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@Data
@NodeEntity
public class Inventory {
    @Id
    private String inventoryId;
    private String orderId;
    private String warehouseId;
    private String status;
    private LocalDateTime preparationTime;
} 