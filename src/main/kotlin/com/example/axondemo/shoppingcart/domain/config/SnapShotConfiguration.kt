package com.example.axondemo.shoppingcart.domain.config

import com.example.axondemo.shoppingcart.domain.aggregate.ShoppingCart
import java.util.concurrent.Executors
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventsourcing.Snapshotter
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SnapShotConfiguration {

    @Bean
    fun snapshotter(): SpringAggregateSnapshotterFactoryBean {
        val snapshotter = SpringAggregateSnapshotterFactoryBean()
        snapshotter.setExecutor(Executors.newSingleThreadExecutor())
        return snapshotter
    }

    @Bean
    fun shoppingCartSnapshotTriggerDefinition(snapshotter: Snapshotter): EventCountSnapshotTriggerDefinition {
        return EventCountSnapshotTriggerDefinition(snapshotter, 5)
    }

    @Bean
    fun shoppingCartEventSourcingRepository(
        eventStore: EventStore,
        snapshotter: Snapshotter
    ): EventSourcingRepository<ShoppingCart> = EventSourcingRepository.builder(ShoppingCart::class.java).eventStore(
        eventStore
    ).build()
}
