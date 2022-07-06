package com.example.axondemo.shoppingcart.adapter.`in`.web.request

import java.util.UUID

data class RemoveItemFromCartRequest(val cartId: UUID, val itemId: UUID)
