package com.crossdoc.retrieval.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.language.OpenAiLanguageModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${openai.api.key}")
    private String openAiApiKey;
    
    @Value("${neo4j.uri}")
    private String neo4jUri;
    
    @Value("${neo4j.username}")
    private String neo4jUsername;
    
    @Value("${neo4j.password}")
    private String neo4jPassword;
    
    @Bean
    public EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }
    
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return ChromaEmbeddingStore.builder()
                .collectionName("order_documents")
                .build();
    }
    
    @Bean
    public LanguageModel languageModel() {
        return OpenAiLanguageModel.builder()
                .apiKey(openAiApiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.1)
                .build();
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(neo4jUri)
                .credentials(neo4jUsername, neo4jPassword)
                .build();
        
        return new SessionFactory(configuration, 
                "com.crossdoc.retrieval.model");
    }
} 