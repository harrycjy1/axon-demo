package com.example.axondemo.member.application.port.`in`

import com.example.axondemo.member.application.port.`in`.command.AddItemToCartCommand
import reactor.core.publisher.Mono

interface AddItemUseCase {

    fun addItemToCart(command: AddItemToCartCommand): Mono<AddItemToCartCommand>

}
