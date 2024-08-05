package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.UUID

data class Transaction(
    val id : UUID = UUID.randomUUID(),
    val accountId: String,
    val amount: Double,
    val merchant: String,
    val mcc: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)