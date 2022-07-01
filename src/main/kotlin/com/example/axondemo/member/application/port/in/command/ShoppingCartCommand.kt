package com.example.axondemo.member.application.port.`in`.command

import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

open class ShoppingCartCommand(
    @TargetAggregateIdentifier open val cartId: UUID
)

class CreateShoppingCartCommand(
    override val cartId: UUID
) : ShoppingCartCommand(cartId)

class AddItemToCartCommand(
    override val cartId: UUID,
    val itemId: UUID
) : ShoppingCartCommand(cartId)

class RemoveItemFromCartCommand(
    override val cartId: UUID,
    val itemId: UUID
) : ShoppingCartCommand(cartId)

