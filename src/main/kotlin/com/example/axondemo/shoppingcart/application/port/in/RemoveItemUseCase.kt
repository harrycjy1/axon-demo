package com.example.axondemo.shoppingcart.application.port.`in`

import com.example.axondemo.shoppingcart.adapter.`in`.web.request.RemoveItemFromCartRequest
import com.example.axondemo.shoppingcart.application.port.`in`.command.RemoveItemFromCartCommand
import reactor.core.publisher.Mono

interface RemoveItemUseCase {

    fun removeItemFromCart(request: RemoveItemFromCartRequest): Mono<RemoveItemFromCartCommand>
}
