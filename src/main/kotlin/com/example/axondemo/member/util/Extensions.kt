package com.example.axondemo.member.util

import java.time.OffsetDateTime
import java.time.ZoneOffset

fun now(): OffsetDateTime = OffsetDateTime.now(ZoneOffset.ofHours(9))
