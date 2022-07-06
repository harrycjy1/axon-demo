package com.example.axondemo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.client.MongoClient
import org.axonframework.commandhandling.DuplicateCommandHandlerResolution
import org.axonframework.commandhandling.DuplicateCommandHandlerResolver
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig(
    private val mongoClient: MongoClient,
    private val mongoProperties: MongoProperties,
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun storageEngine(): EventStorageEngine {
        return MongoEventStorageEngine.builder()
            .eventSerializer(jacksonSerializer())
            .snapshotSerializer(jacksonSerializer())
            .mongoTemplate(mongoTemplate())
            .build()
    }

    @Bean
    fun tokenStore(): TokenStore =
        MongoTokenStore.builder().mongoTemplate(mongoTemplate()).serializer(jacksonSerializer()).build()


    @Bean
    fun duplicateCommandHandlerResolver(): DuplicateCommandHandlerResolver =
        DuplicateCommandHandlerResolution.rejectDuplicates()

    private fun mongoTemplate() = DefaultMongoTemplate.builder()
        .mongoDatabase(mongoClient, mongoProperties.database)
        .domainEventsCollectionName(DOMAIN_EVENT_COLLECTION_NAME)
        .snapshotEventsCollectionName(SNAPSHOT_EVENT_COLLECTION_NAME)
        .trackingTokensCollectionName(TOKEN_COLLECTION_NAME)
        .build()

    private fun jacksonSerializer() = JacksonSerializer.builder().objectMapper(objectMapper).build()


    companion object {
        const val DOMAIN_EVENT_COLLECTION_NAME = "DOMAIN_EVENT"
        const val SNAPSHOT_EVENT_COLLECTION_NAME = "EVENT_SNAPSHOT"
        const val TOKEN_COLLECTION_NAME = "TOKEN"
    }
}
