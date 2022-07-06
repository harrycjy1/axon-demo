package com.example.axondemo.query.adapter.`in`.web

import com.example.axondemo.query.application.port.`in`.ShoppingCartQueryUseCase
import com.example.axondemo.query.application.port.`in`.query.GetAllShoppingCaryQuery
import com.example.axondemo.query.domain.ShoppingCartQuery
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/shopping-cart-query")
class ShoppingCartQueryController(
    private val shoppingCartQueryUseCase: ShoppingCartQueryUseCase,
) {

    @GetMapping
    fun getShoppingCartQuery(): Flux<ShoppingCartQuery> {

        return shoppingCartQueryUseCase.getAllShoppingCarts()
    }
}


