package com.example.axondemo.shoppingcart.application.port.`in`.command

import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.modelling.command.TargetAggregateVersion

open class ShoppingCartCommand(
    @TargetAggregateIdentifier
    open val cartId: UUID,
    @TargetAggregateVersion
    open val targetVersion: Long
)

class CreateShoppingCartCommand(
    override val cartId: UUID,
    override val targetVersion: Long
) : ShoppingCartCommand(cartId, targetVersion)

class AddItemToCartCommand(
    override val cartId: UUID,
    val itemId: UUID,
    override val targetVersion: Long,
) : ShoppingCartCommand(cartId, targetVersion)

class RemoveItemFromCartCommand(
    override val cartId: UUID,
    val itemId: UUID,
    override val targetVersion: Long,
) : ShoppingCartCommand(cartId, targetVersion)

