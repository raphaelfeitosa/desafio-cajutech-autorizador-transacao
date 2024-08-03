package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.domain.Account


fun Account.toEntity() = AccountEntity(
    accountId = this.accountId.toString(),
    name = this.name,
    documentNumber = this.documentNumber,
    food = this.food,
    meal = this.meal,
    cash = this.cash,
    createAt = this.createdAt
)