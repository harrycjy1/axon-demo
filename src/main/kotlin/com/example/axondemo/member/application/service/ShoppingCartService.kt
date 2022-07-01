package com.example.axondemo.member.application.service

import com.example.axondemo.member.application.port.`in`.AddItemUseCase
import com.example.axondemo.member.application.port.`in`.CreateShoppingCartUseCase
import com.example.axondemo.member.application.port.`in`.RemoveItemUseCase
import com.example.axondemo.member.application.port.`in`.command.AddItemToCartCommand
import com.example.axondemo.member.application.port.`in`.command.CreateShoppingCartCommand
import com.example.axondemo.member.application.port.`in`.command.RemoveItemFromCartCommand
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ShoppingCartService(
    private val reactiveCommandGateway: ReactorCommandGateway
) : CreateShoppingCartUseCase, AddItemUseCase, RemoveItemUseCase {
    override fun addItemToCart(command: AddItemToCartCommand): Mono<AddItemToCartCommand> {
        return reactiveCommandGateway.send(command)
    }

    override fun createShoppingCart(command: CreateShoppingCartCommand): Mono<CreateShoppingCartCommand> {
        return reactiveCommandGateway.send(command)
    }

    override fun removeItemFromCart(command: RemoveItemFromCartCommand): Mono<RemoveItemFromCartCommand> {
        return reactiveCommandGateway.send(command)
    }


}
