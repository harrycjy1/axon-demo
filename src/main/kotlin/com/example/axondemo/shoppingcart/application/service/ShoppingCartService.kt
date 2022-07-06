package com.example.axondemo.shoppingcart.application.service

import com.example.axondemo.shoppingcart.adapter.`in`.web.request.AddItemToCartRequest
import com.example.axondemo.shoppingcart.adapter.`in`.web.request.CreateShoppingCartRequest
import com.example.axondemo.shoppingcart.adapter.`in`.web.request.RemoveItemFromCartRequest
import com.example.axondemo.shoppingcart.application.port.`in`.AddItemUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.CreateShoppingCartUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.RemoveItemUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.command.AddItemToCartCommand
import com.example.axondemo.shoppingcart.application.port.`in`.command.CreateShoppingCartCommand
import com.example.axondemo.shoppingcart.application.port.`in`.command.RemoveItemFromCartCommand
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ShoppingCartService(
    private val reactiveCommandGateway: ReactorCommandGateway,
    private val eventStore: EventStore
) : CreateShoppingCartUseCase, AddItemUseCase, RemoveItemUseCase {
    override fun addItemToCart(request: AddItemToCartRequest): Mono<AddItemToCartCommand> {
        return reactiveCommandGateway.send(
            AddItemToCartCommand(
                cartId = request.cartId,
                itemId = request.itemId,
                targetVersion = getVersion(request.cartId.toString())
            )
        )
    }

    override fun createShoppingCart(request: CreateShoppingCartRequest): Mono<CreateShoppingCartCommand> {
        return reactiveCommandGateway.send(
            CreateShoppingCartCommand(
                cartId = request.cartId,
                targetVersion = getVersion (request.cartId.toString())
            )
        )
    }

    override fun removeItemFromCart(request: RemoveItemFromCartRequest): Mono<RemoveItemFromCartCommand> {
        return reactiveCommandGateway.send(
            RemoveItemFromCartCommand(
                cartId = request.cartId,
                itemId = request.itemId,
                targetVersion = getVersion(request.cartId.toString())
            )
        )
    }

    private fun getVersion(identifier: String): Long {
        return eventStore.lastSequenceNumberFor(identifier).orElse(0)
    }


}
