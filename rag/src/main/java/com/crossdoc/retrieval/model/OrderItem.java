package com.crossdoc.retrieval.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity
public class OrderItem {
    @Id
    private String itemId;
    private String productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
} 