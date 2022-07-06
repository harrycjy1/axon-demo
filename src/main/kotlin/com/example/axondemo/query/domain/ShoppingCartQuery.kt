package com.example.axondemo.query.domain

import com.example.axondemo.shoppingcart.domain.aggregate.Item
import com.example.axondemo.shoppingcart.util.now
import java.time.OffsetDateTime
import java.util.UUID
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document("ShoppingCartQuery")
class ShoppingCartQuery(
    @Indexed(unique = true)
    val cartId: UUID,
    val items: MutableList<Item> = mutableListOf(),

    val createdTime: OffsetDateTime = now(),
) {
    var updatedTime: OffsetDateTime = now()

    @MongoId
    private lateinit var _id: ObjectId

    fun addItem(item: Item): ShoppingCartQuery {
        this.items.add(item)
        updatedTime = now()

        return self()
    }

    fun remove(item: Item): ShoppingCartQuery {
        this.items.removeIf { it.itemId == item.itemId }
        updatedTime = now()

        return self()
    }

    private fun self() = this
}
