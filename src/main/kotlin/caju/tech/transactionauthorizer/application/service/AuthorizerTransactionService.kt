package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.AuthorizerTransactionUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.ports.output.TransactionRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthorizerTransactionService(
    private val transactionRepositoryPort: TransactionRepositoryPort,
    private val accountRepositoryPort: AccountRepositoryPort,
) : AuthorizerTransactionUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(AuthorizerTransactionService::class.java.name)
        private const val APPROVED = "00"
        private const val REJECTED = "51"
        private const val ERROR = "07"
    }

    override fun execute(transaction: Transaction): String =
        try {
            logger.info("Starting service to authorize a new transaction: [{}]", transaction)
            val account = accountRepositoryPort.findByAccountId(transaction.accountId)
            val category = getCategoryBalance(transaction.mcc)
            val hasBalance = hasBalance(category, transaction, account)
            if (hasBalance) {
                account.debitBalance(category, transaction)
                accountRepositoryPort.save(account)
                transactionRepositoryPort.save(transaction)
                logger.info("APPROVED transaction. return: [{}]", APPROVED)
                APPROVED
            } else {
                logger.info("REJECTED transaction. return: [{}]", REJECTED)
                REJECTED
            }
        } catch (ex: Exception) {
            logger.error("ERROR process transaction: [{}], message: [{}]", transaction, ex.message)
            ERROR
        }

    private fun getCategoryBalance(mcc: String): String =
        when (mcc) {
            "5411", "5412" -> "FOOD"
            "5811", "5812" -> "MEAL"
            else -> "CASH"
        }

    private fun hasBalance(category: String, transaction: Transaction, account: Account): Boolean =
        when (category) {
            "FOOD" -> account.food >= transaction.amount
            "MEAL" -> account.meal >= transaction.amount
            else -> account.cash >= transaction.amount
        }

}