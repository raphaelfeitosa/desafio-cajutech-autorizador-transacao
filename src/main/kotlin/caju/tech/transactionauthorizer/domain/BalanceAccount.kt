package caju.tech.transactionauthorizer.domain

data class BalanceAccount(
    val accountId: String,
    val food: Double,
    val meal: Double,
    val cash: Double,
)