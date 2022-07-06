package com.example.axondemo.shoppingcart.application.port.`in`

import com.example.axondemo.shoppingcart.adapter.`in`.web.request.CreateShoppingCartRequest
import com.example.axondemo.shoppingcart.application.port.`in`.command.CreateShoppingCartCommand
import reactor.core.publisher.Mono

interface CreateShoppingCartUseCase {

    fun createShoppingCart(request: CreateShoppingCartRequest): Mono<CreateShoppingCartCommand>

}
