package com.example.axondemo.query.adapter.output.persistence

import com.example.axondemo.member.domain.aggregate.Item
import com.example.axondemo.query.application.port.out.CreateShoppingCartQueryPort
import com.example.axondemo.query.application.port.out.GetShoppingCartQueryPort
import com.example.axondemo.query.application.port.out.UpdateShoppingCartQueryPort
import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ShoppingCartQueryRepositoryAdapter(
    private val shoppingCartQueryRepository: ShoppingCartQueryRepository
) : CreateShoppingCartQueryPort, GetShoppingCartQueryPort, UpdateShoppingCartQueryPort {
    override fun createShoppingCartQuery(cartId: UUID): Mono<ShoppingCartQuery> {

        return shoppingCartQueryRepository.save(ShoppingCartQuery(cartId))
    }

    override fun getShoppingCartQueries(): Flux<ShoppingCartQuery> {

        return shoppingCartQueryRepository.findAll()
    }

    override fun addItemToCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery> {

        return shoppingCartQueryRepository.findByCartId(cartId).map {
            it.addItem(Item(itemId))
        }.flatMap(shoppingCartQueryRepository::save)
    }

    override fun removeItemFromCart(cartId: UUID, itemId: UUID): Mono<ShoppingCartQuery> {
        return shoppingCartQueryRepository.findByCartId(cartId).map {
            it.remove(Item(itemId))
        }.flatMap(shoppingCartQueryRepository::save)
    }
}
