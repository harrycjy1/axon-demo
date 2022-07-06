package com.example.axondemo.query.application.service

import com.example.axondemo.query.application.port.`in`.query.GetAllShoppingCaryQuery
import com.example.axondemo.query.application.port.out.GetShoppingCartQueryPort
import com.example.axondemo.query.domain.ShoppingCartQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class ShoppingCartQueryQueryHandler(
    private val getShoppingCartQueryPort: GetShoppingCartQueryPort
) {

    @QueryHandler
    fun getAllShoppingCartsByQuery(query: GetAllShoppingCaryQuery): List<ShoppingCartQuery> {
        return getShoppingCartQueryPort.getShoppingCartQueries().map { it }.toIterable().toList()
    }
}
