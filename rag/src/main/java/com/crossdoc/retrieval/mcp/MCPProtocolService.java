package com.crossdoc.retrieval.mcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MCPProtocolService {

    public MCPMessage createMessage(String source, String destination, 
                                   String messageType, Map<String, Object> payload) {
        return MCPMessage.builder()
                .messageId(UUID.randomUUID().toString())
                .source(source)
                .destination(destination)
                .messageType(messageType)
                .timestamp(LocalDateTime.now())
                .payload(payload)
                .build();
    }
    
    public void sendMessage(MCPMessage message) {
        // 实际实现中会通过消息队列或HTTP等方式发送
        log.info("Sending MCP message: {}", message);
    }
    
    public MCPMessage requestOrderInfo(String orderId, String department) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        
        return createMessage(
                "retrieval-service",
                department,
                "ORDER_INFO_REQUEST",
                payload
        );
    }
} 