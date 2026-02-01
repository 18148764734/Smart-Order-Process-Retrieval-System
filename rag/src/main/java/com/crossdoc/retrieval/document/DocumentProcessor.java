package com.crossdoc.retrieval.document;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentProcessor {
    
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;
    
    // 文档解析器
    private final DocumentParser documentParser = new TextDocumentParser();
    
    // 文档分割器 - 使用重叠分割以保持上下文
    private final DocumentSplitter splitter = DocumentSplitters.recursive(500, 100);
    
    public void processDocument(Path filePath, String department) {
        Document document = documentParser.parse(filePath);
        document = document.copy(document.text(), 
                document.metadata().add("department", department));
        
        List<TextSegment> segments = splitter.split(document);
        embeddingStore.addAll(embeddingModel.embedAll(segments).content());
    }
} 