package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.*

data class Merchant(
    val merchantId: UUID = UUID.randomUUID(),
    val name: String,
    val categories: Set<String>? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now(),
)