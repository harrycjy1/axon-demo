package com.example.axondemo.member.application.port.`in`

import com.example.axondemo.member.application.port.`in`.command.RemoveItemFromCartCommand
import reactor.core.publisher.Mono

interface RemoveItemUseCase {

    fun removeItemFromCart(command: RemoveItemFromCartCommand): Mono<RemoveItemFromCartCommand>
}
