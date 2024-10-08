package caju.tech.transactionauthorizer.adapter.ports.input.api.converter

import caju.tech.transactionauthorizer.adapter.ports.input.api.request.AuthorizerTransactionRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateMerchantRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.BalanceAccount
import caju.tech.transactionauthorizer.domain.Merchant
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
    foodAmount = this.food,
    mealAmount = this.meal,
    cashAmount = this.cash
)

fun CreateMerchantRequest.toDomain() = Merchant(
    name = this.name,
    categories = this.categories.let { categories -> categories!!.map { it } }
)