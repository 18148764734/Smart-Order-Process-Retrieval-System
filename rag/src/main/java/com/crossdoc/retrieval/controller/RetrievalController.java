package com.crossdoc.retrieval.controller;

import com.crossdoc.retrieval.service.CrossDocumentRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/retrieval")
@RequiredArgsConstructor
public class RetrievalController {

    private final CrossDocumentRetrievalService retrievalService;
    
    @GetMapping("/order-flow")
    public String getOrderFlow(
            @RequestParam String question,
            @RequestParam String orderId) {
        return retrievalService.processOrderFlowQuery(question, orderId);
    }
} 