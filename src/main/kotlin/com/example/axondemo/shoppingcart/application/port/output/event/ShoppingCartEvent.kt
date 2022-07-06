package com.example.axondemo.shoppingcart.application.port.output.event

import java.time.OffsetDateTime
import java.util.UUID
import com.example.axondemo.shoppingcart.util.now

interface ShoppingCartEvent {
    val cartId: UUID
    val createdTime: OffsetDateTime
}

class ShoppingCartCreatedEvent(
    override val cartId: UUID,
    override val createdTime: OffsetDateTime = now(),
) : ShoppingCartEvent

class ItemAddedToCartEvent(
    override val cartId: UUID,
    val itemId: UUID,
    override val createdTime: OffsetDateTime = now()
) : ShoppingCartEvent

class ItemRemovedFromCartEvent(
    override val cartId: UUID,
    val itemId: UUID,
    override val createdTime: OffsetDateTime = now()
) : ShoppingCartEvent
