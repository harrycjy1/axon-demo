package com.example.axondemo.member.domain.aggregate

import com.example.axondemo.member.application.port.`in`.command.AddItemToCartCommand
import com.example.axondemo.member.application.port.`in`.command.CreateShoppingCartCommand
import com.example.axondemo.member.application.port.`in`.command.RemoveItemFromCartCommand
import com.example.axondemo.member.application.port.output.event.ItemAddedToCartEvent
import com.example.axondemo.member.application.port.output.event.ItemRemovedFromCartEvent
import com.example.axondemo.member.application.port.output.event.ShoppingCartCreatedEvent
import java.util.UUID
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate(
    snapshotTriggerDefinition = "shoppingCartSnapshotTriggerDefinition",
)
class ShoppingCart {

    @AggregateIdentifier
    private lateinit var cartId: UUID

    private val items: MutableList<Item> = mutableListOf()

    // axon required
    protected constructor()

    @CommandHandler
    protected constructor(command: CreateShoppingCartCommand) {
        AggregateLifecycle.apply(
            ShoppingCartCreatedEvent(
                cartId = command.cartId
            )
        )
    }

    @CommandHandler
    protected fun handle(command: AddItemToCartCommand) {
        AggregateLifecycle.apply(
            ItemAddedToCartEvent(
                cartId = command.cartId, itemId = command.itemId
            )
        )
    }

    @CommandHandler
    protected fun handle(command: RemoveItemFromCartCommand) {
        AggregateLifecycle.apply(
            ItemRemovedFromCartEvent(
                cartId = command.cartId, itemId = command.itemId
            )
        )
    }

    @EventSourcingHandler
    protected fun on(event: ShoppingCartCreatedEvent) {
        this.cartId = event.cartId
    }

    @EventSourcingHandler
    protected fun on(event: ItemAddedToCartEvent) {
        this.items.add(
            Item(event.itemId)
        )
    }

    @EventSourcingHandler
    protected fun on(event: ItemRemovedFromCartEvent) {
        this.items.removeIf { it.itemId == event.itemId }
    }


}
