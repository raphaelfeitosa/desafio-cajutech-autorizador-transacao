package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.*

data class Account(
    val accountId: UUID = UUID.randomUUID(),
    val name: String,
    val documentNumber: String,
    val food: Double = 0.0,
    val meal: Double = 0.0,
    val cash: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)