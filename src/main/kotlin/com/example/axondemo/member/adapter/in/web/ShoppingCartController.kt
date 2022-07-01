package com.example.axondemo.member.adapter.`in`.web

import com.example.axondemo.member.adapter.`in`.web.request.AddItemToCartRequest
import com.example.axondemo.member.adapter.`in`.web.request.RemoveItemFromCartRequest
import com.example.axondemo.member.application.port.`in`.AddItemUseCase
import com.example.axondemo.member.application.port.`in`.CreateShoppingCartUseCase
import com.example.axondemo.member.application.port.`in`.RemoveItemUseCase
import com.example.axondemo.member.application.port.`in`.command.AddItemToCartCommand
import com.example.axondemo.member.application.port.`in`.command.CreateShoppingCartCommand
import com.example.axondemo.member.application.port.`in`.command.RemoveItemFromCartCommand
import java.util.UUID
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/shopping-cart")
class ShoppingCartController(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase
) {

    @PostMapping
    fun createShoppingCart(): Mono<CreateShoppingCartCommand> {
        return createShoppingCartUseCase.createShoppingCart(
            CreateShoppingCartCommand(
                cartId = UUID.randomUUID()
            )
        )
    }

    @PutMapping("/add/item")
    fun addItemToCart(
        @RequestBody request: AddItemToCartRequest
    ): Mono<AddItemToCartCommand> {
        return addItemUseCase.addItemToCart(
            command = AddItemToCartCommand(cartId = request.cartId, itemId = UUID.randomUUID())
        )
    }

    @PutMapping("/remove/item")
    fun removeItemFromCart(
        @RequestBody request: RemoveItemFromCartRequest
    ): Mono<RemoveItemFromCartCommand> {
        return removeItemUseCase.removeItemFromCart(
            RemoveItemFromCartCommand(
                cartId = request.cartId,
                itemId = request.itemId
            )
        )
    }
}
