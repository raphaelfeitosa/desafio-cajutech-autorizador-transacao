package caju.tech.transactionauthorizer.adapter.ports.input.request

data class CreateAccountRequest(
    val name: String,
    val documentNumber: String
)