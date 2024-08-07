package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.MerchantEntity
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Merchant
import java.util.*


fun AccountEntity.toDomain() = Account(
    accountId = UUID.fromString(this.accountId),
    name = this.name!!,
    documentNumber = this.documentNumber!!,
    foodAmount = this.foodAmount!!,
    mealAmount = this.mealAmount!!,
    cashAmount = this.cashAmount!!,
    totalAmount = this.totalAmount!!,
    createdAt = this.createAt!!,
    updateAt = this.updateAt
)

fun MerchantEntity.toDomain() = Merchant(
    merchantId = UUID.fromString(this.merchantId),
    name = this.name!!,
    categories = this.categories,
    createdAt = this.createAt!!,
    updateAt = this.updateAt
)