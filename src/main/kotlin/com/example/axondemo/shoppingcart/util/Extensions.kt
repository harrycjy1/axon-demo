package com.example.axondemo.shoppingcart.util

import java.time.OffsetDateTime
import java.time.ZoneOffset

fun now(): OffsetDateTime = OffsetDateTime.now(ZoneOffset.ofHours(9))
