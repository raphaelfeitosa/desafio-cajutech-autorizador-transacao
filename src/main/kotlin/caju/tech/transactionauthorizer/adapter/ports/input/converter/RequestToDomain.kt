package caju.tech.transactionauthorizer.adapter.ports.input.converter

import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.BalanceAccount


fun CreateAccountRequest.toDomain() = Account(
    name = this.name,
    documentNumber = this.documentNumber
)

fun UpdateBalanceAccountRequest.toDomain(accountId: String) = BalanceAccount(
    accountId = accountId,
    food = this.food,
    meal = this.meal,
    cash = this.cash
)