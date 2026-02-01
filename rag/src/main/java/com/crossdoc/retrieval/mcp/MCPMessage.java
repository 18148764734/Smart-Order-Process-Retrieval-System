package com.crossdoc.retrieval.mcp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class MCPMessage {
    private String messageId;
    private String source;
    private String destination;
    private String messageType;
    private LocalDateTime timestamp;
    private Map<String, Object> payload;
} 