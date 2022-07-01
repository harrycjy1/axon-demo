package com.example.axondemo.member.application.port.`in`

import com.example.axondemo.member.application.port.`in`.command.CreateShoppingCartCommand
import reactor.core.publisher.Mono

interface CreateShoppingCartUseCase {

    fun createShoppingCart(command: CreateShoppingCartCommand): Mono<CreateShoppingCartCommand>

}
