package caju.tech.transactionauthorizer.adapter.ports.input.request

data class UpdateBalanceAccountRequest(
    val food: Double = 0.0,
    val meal: Double = 0.0,
    val cash: Double = 0.0
)