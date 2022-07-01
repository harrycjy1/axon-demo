package com.example.axondemo.query.adapter.`in`.handler

import com.example.axondemo.member.application.port.output.event.ItemAddedToCartEvent
import com.example.axondemo.member.application.port.output.event.ItemRemovedFromCartEvent
import com.example.axondemo.member.application.port.output.event.ShoppingCartCreatedEvent
import com.example.axondemo.query.application.port.`in`.ShoppingCartQueryUseCase
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class ShoppingCartQueryHandler(
    private val shoppingCartQueryUseCase: ShoppingCartQueryUseCase
) {

    @EventHandler
    fun on(event: ShoppingCartCreatedEvent) {
        shoppingCartQueryUseCase.createShoppingCartQuery(event.cartId).subscribe()
    }

    @EventHandler
    fun on(event: ItemAddedToCartEvent) {
        shoppingCartQueryUseCase.addItemToCart(event.cartId, event.itemId).subscribe()
    }

    @EventHandler
    fun on(event: ItemRemovedFromCartEvent) {
        shoppingCartQueryUseCase.removeItemFromCart(event.cartId, event.itemId).subscribe()
    }
}
