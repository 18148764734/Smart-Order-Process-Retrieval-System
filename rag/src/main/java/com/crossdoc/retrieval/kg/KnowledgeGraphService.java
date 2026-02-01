package com.crossdoc.retrieval.kg;

import com.crossdoc.retrieval.model.*;
import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KnowledgeGraphService {

    private final SessionFactory sessionFactory;
    
    public void saveOrderWithRelations(Order order) {
        Session session = sessionFactory.openSession();
        session.save(order, 2); // 深度为2，保存关联实体
    }
    
    public Order getOrderWithFullProcess(String orderId) {
        Session session = sessionFactory.openSession();
        return session.load(Order.class, orderId, 2);
    }
    
    public Map<String, Object> getOrderProcessFlow(String orderId) {
        Session session = sessionFactory.openSession();
        
        String query = "MATCH (o:Order {orderId: $orderId})-[r]->(n) " +
                "RETURN type(r) as relation, n";
        
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        
        return session.query(query, params).queryResults();
    }
    
    // 使用Cypher查询获取订单全流程
    public String getOrderFullProcessDescription(String orderId) {
        Session session = sessionFactory.openSession();
        
        String query = "MATCH p=(o:Order {orderId: $orderId})-[*]->(n) " +
                "RETURN p";
        
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        
        // 处理结果并生成描述
        // ...
        
        return "订单全流程描述..."; // 实际实现中会根据查询结果生成
    }
} 