package caju.tech.transactionauthorizer.adapter.ports.input.api.converter

import caju.tech.transactionauthorizer.adapter.ports.input.api.response.AccountIdResponse
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.TransactionResponse
import caju.tech.transactionauthorizer.domain.Account

fun String.toResponse() = TransactionResponse(
    code = this
)

fun Account.toResponse() = AccountIdResponse(
    accountId = this.accountId
)