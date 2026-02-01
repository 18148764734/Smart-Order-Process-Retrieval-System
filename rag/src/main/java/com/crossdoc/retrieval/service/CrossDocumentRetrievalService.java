package com.crossdoc.retrieval.service;

import com.crossdoc.retrieval.kg.KnowledgeGraphService;
import com.crossdoc.retrieval.mcp.MCPMessage;
import com.crossdoc.retrieval.mcp.MCPProtocolService;
import com.crossdoc.retrieval.model.Order;
import com.crossdoc.retrieval.rag.RAGService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CrossDocumentRetrievalService {

    private final RAGService ragService;
    private final KnowledgeGraphService knowledgeGraphService;
    private final MCPProtocolService mcpProtocolService;
    
    /**
     * 处理订单全流程查询
     */
    public String processOrderFlowQuery(String question, String orderId) {
        // 1. 从知识图谱获取订单基本信息
        Order order = knowledgeGraphService.getOrderWithFullProcess(orderId);
        
        // 2. 使用MCP协议向各部门请求详细信息
        requestDepartmentInfo(orderId);
        
        // 3. 使用RAG从各部门文档中检索相关信息
        String ragResult = ragService.query(question);
        
        // 4. 整合知识图谱和RAG结果
        String kgResult = knowledgeGraphService.getOrderFullProcessDescription(orderId);
        
        // 5. 生成最终回答
        return generateFinalAnswer(question, ragResult, kgResult, order);
    }
    
    private void requestDepartmentInfo(String orderId) {
        // 向各部门发送MCP消息请求信息
        mcpProtocolService.sendMessage(
                mcpProtocolService.requestOrderInfo(orderId, "order-department"));
        
        mcpProtocolService.sendMessage(
                mcpProtocolService.requestOrderInfo(orderId, "payment-department"));
        
        mcpProtocolService.sendMessage(
                mcpProtocolService.requestOrderInfo(orderId, "inventory-department"));
        
        mcpProtocolService.sendMessage(
                mcpProtocolService.requestOrderInfo(orderId, "fulfillment-department"));
        
        mcpProtocolService.sendMessage(
                mcpProtocolService.requestOrderInfo(orderId, "delivery-department"));
    }
    
    private String generateFinalAnswer(String question, String ragResult, 
                                      String kgResult, Order order) {
        // 构建提示，整合所有信息
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        params.put("ragResult", ragResult);
        params.put("kgResult", kgResult);
        params.put("order", order);
        
        // 使用RAG服务生成最终答案
        return ragService.query("基于以下信息回答关于订单" + order.getOrderId() + 
                "的问题：\n知识图谱信息：" + kgResult + "\n检索信息：" + ragResult);
    }
} 