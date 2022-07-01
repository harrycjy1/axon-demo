package com.example.axondemo.query.application.port.out

import com.example.axondemo.query.domain.ShoppingCartQuery
import reactor.core.publisher.Flux

interface GetShoppingCartQueryPort {

    fun getShoppingCartQueries(): Flux<ShoppingCartQuery>

}
