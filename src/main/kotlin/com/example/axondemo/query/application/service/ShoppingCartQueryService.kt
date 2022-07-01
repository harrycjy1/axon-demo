package com.example.axondemo.query.application.service

import com.example.axondemo.query.application.port.`in`.ShoppingCartQueryUseCase
import com.example.axondemo.query.application.port.out.CreateShoppingCartQueryPort
import com.example.axondemo.query.application.port.out.GetShoppingCartQueryPort
import com.example.axondemo.query.application.port.out.UpdateShoppingCartQueryPort
import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShoppingCartQueryService(
    private val createShoppingCartQueryPort: CreateShoppingCartQueryPort,
    private val getShoppingCartQueryPort: GetShoppingCartQueryPort,
    private val updateShoppingCartQueryPort: UpdateShoppingCartQueryPort
) : ShoppingCartQueryUseCase {

    override fun createShoppingCartQuery(cartId: UUID): Mono<ShoppingCartQuery> {
        return createShoppingCartQueryPort.createShoppingCartQuery(cartId)
    }

    override fun getAllShoppingCarts(): Flux<ShoppingCartQuery> {
        return getShoppingCartQueryPort.getShoppingCartQueries()
    }

    override fun addItemToCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery> {
        return updateShoppingCartQueryPort.addItemToCart(cartId, itemId)
    }

    override fun removeItemFromCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery> {
        return updateShoppingCartQueryPort.removeItemFromCart(cartId, itemId)
    }
}
