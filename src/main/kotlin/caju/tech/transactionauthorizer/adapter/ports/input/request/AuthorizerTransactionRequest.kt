package caju.tech.transactionauthorizer.adapter.ports.input.request

data class AuthorizerTransactionRequest(
    val accountId: String,
    val amount: Double,
    val merchant: String,
    val mcc: String,
)