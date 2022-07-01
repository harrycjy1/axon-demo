package com.example.axondemo.query.application.port.out

import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import reactor.core.publisher.Mono

interface UpdateShoppingCartQueryPort {

    fun addItemToCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery>

    fun removeItemFromCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery>
}
