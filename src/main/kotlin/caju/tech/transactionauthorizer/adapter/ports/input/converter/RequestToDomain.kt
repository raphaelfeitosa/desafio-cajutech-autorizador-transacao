package caju.tech.transactionauthorizer.adapter.ports.input.converter

import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.domain.Account


fun CreateAccountRequest.toDomain() = Account(
    name = this.name,
    documentNumber = this.documentNumber
)