package com.example.axondemo.shoppingcart.application.port.`in`

import com.example.axondemo.shoppingcart.adapter.`in`.web.request.AddItemToCartRequest
import com.example.axondemo.shoppingcart.application.port.`in`.command.AddItemToCartCommand
import reactor.core.publisher.Mono

interface AddItemUseCase {

    fun addItemToCart(request: AddItemToCartRequest): Mono<AddItemToCartCommand>

}
