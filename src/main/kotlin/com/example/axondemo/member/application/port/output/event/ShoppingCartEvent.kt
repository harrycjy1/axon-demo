package com.example.axondemo.member.application.port.output.event

import java.time.OffsetDateTime
import java.util.UUID
import com.example.axondemo.member.util.now

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
