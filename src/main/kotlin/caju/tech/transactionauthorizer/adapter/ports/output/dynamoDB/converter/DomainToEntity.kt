package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.TransactionEntity
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Transaction

fun Transaction.toEntity() = TransactionEntity(
    transactionId = this.id.toString(),
    accountId = this.accountId,
    amount = this.amount,
    merchant = this.merchant,
    mcc = this.mcc,
    createAt = this.createdAt
)

fun Account.toEntity() = AccountEntity(
    accountId = this.accountId.toString(),
    name = this.name,
    documentNumber = this.documentNumber,
    food = this.food,
    meal = this.meal,
    cash = this.cash,
    createAt = this.createdAt
)