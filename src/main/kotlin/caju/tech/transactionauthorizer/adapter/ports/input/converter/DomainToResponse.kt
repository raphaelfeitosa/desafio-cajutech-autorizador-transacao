package caju.tech.transactionauthorizer.adapter.ports.input.converter

import caju.tech.transactionauthorizer.adapter.ports.input.response.AccountIdResponse
import caju.tech.transactionauthorizer.domain.Account


fun Account.toResponse() = AccountIdResponse(
    accountId = this.accountId
)