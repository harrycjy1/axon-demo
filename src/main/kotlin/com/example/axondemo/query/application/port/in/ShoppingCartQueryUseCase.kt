package com.example.axondemo.query.application.port.`in`

import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ShoppingCartQueryUseCase {

    fun createShoppingCartQuery(cartId: UUID): Mono<ShoppingCartQuery>

    fun getAllShoppingCarts(): Flux<ShoppingCartQuery>
    fun addItemToCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery>

    fun removeItemFromCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery>
}

