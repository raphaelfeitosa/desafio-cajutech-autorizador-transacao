package caju.tech.transactionauthorizer.domain

data class BalanceAccount(
    val accountId: String,
    val foodAmount: Double?,
    val mealAmount: Double?,
    val cashAmount: Double?,
)