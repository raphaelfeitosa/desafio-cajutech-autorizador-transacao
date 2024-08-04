package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.domain.Account
import java.util.*


fun AccountEntity.toDomain() = Account(
    accountId = UUID.fromString(this.accountId),
    name = this.name!!,
    documentNumber = this.documentNumber!!,
    food = this.food!!,
    meal = this.meal!!,
    cash = this.cash!!,
    createdAt = this.createAt!!,
    updateAt = this.updateAt
)