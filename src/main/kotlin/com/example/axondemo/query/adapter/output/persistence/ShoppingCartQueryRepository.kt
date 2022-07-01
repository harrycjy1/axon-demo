package com.example.axondemo.query.adapter.output.persistence

import com.example.axondemo.query.domain.ShoppingCartQuery
import java.util.UUID
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ShoppingCartQueryRepository : ReactiveMongoRepository<ShoppingCartQuery, ObjectId>, ReactiveCrudRepository<ShoppingCartQuery, ObjectId> {

    fun findByCartId(cartId: UUID): Mono<ShoppingCartQuery>
}
