package caju.tech.transactionauthorizer.domain

import java.time.LocalDateTime
import java.util.*

data class Account(
    val accountId: UUID = UUID.randomUUID(),
    val name: String,
    val documentNumber: String,
    var food: Double = 0.0,
    var meal: Double = 0.0,
    var cash: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now(),
) {
    fun updateBalance(balanceAccount: BalanceAccount) {
        balanceAccount.food?.let { this.food += it }
        balanceAccount.meal?.let { this.meal += it }
        balanceAccount.cash?.let { this.cash += it }
    }

    fun debitBalance(category: String, transaction: Transaction) {
        when (category) {
            "FOOD" -> this.food -= transaction.amount
            "MEAL" -> this.meal -= transaction.amount
            else -> this.cash -= transaction.amount
        }
    }

    fun hasBalance(category: String, transaction: Transaction): Boolean =
        when (category) {
            "FOOD" -> this.food >= transaction.amount
            "MEAL" -> this.meal >= transaction.amount
            else -> this.cash >= transaction.amount
        }

    fun hasBalanceCash(transaction: Transaction): Boolean = this.cash >= transaction.amount

    fun getCategoryBalance(mcc: String): String =
        when (mcc) {
            "5411", "5412" -> "FOOD"
            "5811", "5812" -> "MEAL"
            else -> "CASH"
        }

}