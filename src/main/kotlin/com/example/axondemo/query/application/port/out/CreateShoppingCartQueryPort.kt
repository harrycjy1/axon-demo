package com.example.axondemo.query.application.port.out

import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import reactor.core.publisher.Mono

interface CreateShoppingCartQueryPort {

    fun createShoppingCartQuery(cartId: UUID): Mono<ShoppingCartQuery>

}
