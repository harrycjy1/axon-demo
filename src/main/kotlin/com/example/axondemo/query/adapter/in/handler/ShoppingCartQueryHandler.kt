package com.example.axondemo.query.adapter.`in`.handler

import com.example.axondemo.shoppingcart.application.port.output.event.ItemAddedToCartEvent
import com.example.axondemo.shoppingcart.application.port.output.event.ItemRemovedFromCartEvent
import com.example.axondemo.shoppingcart.application.port.output.event.ShoppingCartCreatedEvent
import com.example.axondemo.query.adapter.output.persistence.ShoppingCartQueryRepository
import com.example.axondemo.query.application.port.`in`.ShoppingCartQueryUseCase
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.AllowReplay
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.ResetHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("shoppingCartQuery")
class ShoppingCartQueryHandler(
    private val shoppingCartQueryUseCase: ShoppingCartQueryUseCase,
    private val shoppingCartQueryRepository: ShoppingCartQueryRepository
) {

    @EventHandler
    @AllowReplay
    fun on(event: ShoppingCartCreatedEvent) {
        shoppingCartQueryUseCase.createShoppingCartQuery(event.cartId).subscribe()
    }

    @EventHandler
    @AllowReplay
    fun on(event: ItemAddedToCartEvent) {
        shoppingCartQueryUseCase.addItemToCart(event.cartId, event.itemId).subscribe()
    }

    @EventHandler
    @AllowReplay
    fun on(event: ItemRemovedFromCartEvent) {
        shoppingCartQueryUseCase.removeItemFromCart(event.cartId, event.itemId).subscribe()
    }

    @ResetHandler
    fun on() {
        shoppingCartQueryRepository.deleteAll().subscribe()
    }
}
