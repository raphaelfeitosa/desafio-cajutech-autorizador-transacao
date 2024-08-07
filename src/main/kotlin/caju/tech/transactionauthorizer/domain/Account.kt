package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.*

data class Account(
    val accountId: UUID = UUID.randomUUID(),
    val name: String,
    val documentNumber: String,
    var foodAmount: Double = 0.0,
    var mealAmount: Double = 0.0,
    var cashAmount: Double = 0.0,
    var totalAmount: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now(),
) {
    fun updateBalance(balanceAccount: BalanceAccount) {
        balanceAccount.foodAmount?.let { this.foodAmount += it }
        balanceAccount.mealAmount?.let { this.mealAmount += it }
        balanceAccount.cashAmount?.let { this.cashAmount += it }

        this.totalAmount = (this.foodAmount + this.mealAmount + this.cashAmount)
    }

    fun debitBalance(category: String, transaction: Transaction) {
        when (category) {
            "FOOD" -> {
                this.foodAmount -= transaction.amount
                this.totalAmount -= transaction.amount
            }

            "MEAL" -> {
                this.mealAmount -= transaction.amount
                this.totalAmount -= transaction.amount
            }

            else -> {
                this.cashAmount -= transaction.amount
                this.totalAmount -= transaction.amount
            }
        }
    }

    fun hasBalance(category: String, transaction: Transaction): Boolean =
        when (category) {
            "FOOD" -> this.foodAmount >= transaction.amount
            "MEAL" -> this.mealAmount >= transaction.amount
            "CASH" -> this.cashAmount >= transaction.amount
            else -> false
        }

    fun hasBalanceCash(transaction: Transaction): Boolean = this.cashAmount >= transaction.amount

    fun getCategory(transaction: Transaction): String =
        when (transaction.mcc) {
            "5411", "5412" -> "FOOD"
            "5811", "5812" -> "MEAL"
            else -> "INVALID_CATEGORY"
        }
}