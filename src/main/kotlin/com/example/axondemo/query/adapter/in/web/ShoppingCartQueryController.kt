package com.example.axondemo.query.adapter.`in`.web

import com.example.axondemo.query.application.port.`in`.ShoppingCartQueryUseCase
import com.example.axondemo.query.domain.ShoppingCartQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ShoppingCartQueryController(
    private val shoppingCartQueryUseCase: ShoppingCartQueryUseCase
) {

    @GetMapping
    fun getShoppingCartQuery(): Flux<ShoppingCartQuery> {

        return shoppingCartQueryUseCase.getAllShoppingCarts()
    }
}


