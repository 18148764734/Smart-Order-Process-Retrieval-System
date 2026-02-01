package com.crossdoc.retrieval.rag;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.retriever.Retriever;
import dev.langchain4j.service.PromptTemplate;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RAGService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final LanguageModel languageModel;
    
    private static final String PROMPT_TEMPLATE = 
            "你是一个订单处理专家。根据以下信息回答问题：\n\n" +
            "上下文信息:\n{{context}}\n\n" +
            "问题: {{question}}\n\n" +
            "请提供详细且准确的回答，只使用上下文中提供的信息。如果上下文中没有足够信息，请说明无法回答。";
    
    public String query(String question) {
        // 创建检索器
        Retriever<TextSegment> retriever = Retriever.from(
                embeddingStore,
                embeddingModel,
                5  // 检索前5个最相关的文档片段
        );
        
        // 检索相关文档
        List<TextSegment> relevantSegments = retriever.retrieve(question);
        
        // 构建上下文
        String context = relevantSegments.stream()
                .map(TextSegment::text)
                .collect(Collectors.joining("\n\n"));
        
        // 创建提示模板
        PromptTemplate promptTemplate = PromptTemplate.from(PROMPT_TEMPLATE);
        
        // 填充模板
        String prompt = promptTemplate.apply(params -> {
            params.put("context", context);
            params.put("question", question);
            return params;
        });
        
        // 调用语言模型
        Response<String> response = languageModel.generate(prompt);
        
        return response.content();
    }
} 