package caju.tech.transactionauthorizer.adapter.ports.input.converter

import caju.tech.transactionauthorizer.adapter.ports.input.response.CreateAccountResponse
import caju.tech.transactionauthorizer.domain.Account


fun Account.toResponse() = CreateAccountResponse(
    accountId = this.accountId
)