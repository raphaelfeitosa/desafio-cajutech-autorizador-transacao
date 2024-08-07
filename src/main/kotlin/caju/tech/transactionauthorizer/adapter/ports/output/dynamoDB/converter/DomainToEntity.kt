package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.MerchantEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.TransactionEntity
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.domain.Transaction

fun Transaction.toEntity() = TransactionEntity(
    transactionId = this.id.toString(),
    accountId = this.accountId,
    merchantId = this.merchant,
    amount = this.amount,
    merchant = this.merchant,
    mcc = this.mcc,
    createAt = this.createdAt
)

fun Account.toEntity() = AccountEntity(
    accountId = this.accountId.toString(),
    name = this.name,
    documentNumber = this.documentNumber,
    foodAmount = this.foodAmount,
    mealAmount = this.mealAmount,
    cashAmount = this.cashAmount,
    totalAmount = this.totalAmount,
    createAt = this.createdAt
)

fun Merchant.toEntity() = MerchantEntity(
    merchantId = this.merchantId.toString(),
    name = this.name,
    categories = this.categories,
    createAt = this.createdAt
)