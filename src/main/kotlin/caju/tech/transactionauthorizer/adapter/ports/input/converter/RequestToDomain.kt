package caju.tech.transactionauthorizer.adapter.ports.input.converter

import caju.tech.transactionauthorizer.adapter.ports.input.request.AuthorizerTransactionRequest
import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.BalanceAccount
import caju.tech.transactionauthorizer.domain.Transaction

fun AuthorizerTransactionRequest.toDomain() = Transaction(
    accountId = this.accountId,
    amount = this.amount,
    merchant = this.merchant,
    mcc = this.mcc
)

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