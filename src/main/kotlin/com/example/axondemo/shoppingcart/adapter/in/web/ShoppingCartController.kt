package com.example.axondemo.shoppingcart.adapter.`in`.web

import com.example.axondemo.shoppingcart.adapter.`in`.web.request.AddItemToCartRequest
import com.example.axondemo.shoppingcart.adapter.`in`.web.request.CreateShoppingCartRequest
import com.example.axondemo.shoppingcart.adapter.`in`.web.request.RemoveItemFromCartRequest
import com.example.axondemo.shoppingcart.application.port.`in`.AddItemUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.CreateShoppingCartUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.RemoveItemUseCase
import com.example.axondemo.shoppingcart.application.port.`in`.command.AddItemToCartCommand
import com.example.axondemo.shoppingcart.application.port.`in`.command.CreateShoppingCartCommand
import com.example.axondemo.shoppingcart.application.port.`in`.command.RemoveItemFromCartCommand
import com.example.axondemo.shoppingcart.application.port.output.event.ShoppingCartEvent
import com.example.axondemo.shoppingcart.application.service.ShoppingCartService
import org.axonframework.config.Configuration
import org.axonframework.eventhandling.TrackingEventProcessor
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/shopping-cart")
class ShoppingCartController(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val configuration: Configuration,
    private val shoppingCartService: ShoppingCartService
) {

    @PostMapping
    fun createShoppingCart(
        @RequestBody request: CreateShoppingCartRequest
    ): Mono<CreateShoppingCartCommand> {
        return createShoppingCartUseCase.createShoppingCart(request)
    }

    @PutMapping("/add/item")
    fun addItemToCart(
        @RequestBody request: AddItemToCartRequest
    ): Mono<AddItemToCartCommand> {
        return addItemUseCase.addItemToCart(request)
    }

    @PutMapping("/remove/item")
    fun removeItemFromCart(
        @RequestBody request: RemoveItemFromCartRequest
    ): Mono<RemoveItemFromCartCommand> {
        return removeItemUseCase.removeItemFromCart(request)
    }

    @DeleteMapping("/reset")
    fun reset() {
        configuration.eventProcessingConfiguration()
            .eventProcessorByProcessingGroup(
                "shoppingCartQuery",
                TrackingEventProcessor::class.java
            )
            .ifPresent { trackingEventProcessor: TrackingEventProcessor ->
                trackingEventProcessor.shutDown()
                trackingEventProcessor.resetTokens() // (1)
                trackingEventProcessor.start()
            }
    }

    @PostMapping("/test")
    fun test(
        @RequestParam identifier: String,
    ) {

        val result: ShoppingCartEvent = shoppingCartService.readEvents(identifier) as ShoppingCartEvent

        result
    }
}
