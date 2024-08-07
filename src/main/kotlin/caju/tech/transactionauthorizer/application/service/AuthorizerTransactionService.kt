package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.AuthorizerTransactionUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.ports.output.LockPort
import caju.tech.transactionauthorizer.application.ports.output.MerchantRepositoryPort
import caju.tech.transactionauthorizer.application.ports.output.TransactionRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Transaction
import caju.tech.transactionauthorizer.domain.withLock
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthorizerTransactionService(
    private val transactionRepositoryPort: TransactionRepositoryPort,
    private val accountRepositoryPort: AccountRepositoryPort,
    private val merchantRepositoryPort: MerchantRepositoryPort,
    private val lockPort: LockPort,
) : AuthorizerTransactionUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(AuthorizerTransactionService::class.java.name)
        private const val APPROVED = "00"
        private const val REJECTED = "51"
        private const val ERROR = "07"
    }

    override fun execute(transaction: Transaction): String =
        withLock("${transaction.accountId}-${AuthorizerTransactionService::class.java.name}", lockPort) {
            try {
                logger.info("Starting service to authorize a new transaction: [{}]", transaction)

                val account = accountRepositoryPort.findByAccountId(transaction.accountId)
                val categoryAccount = account.getCategory(transaction)

                if (account.hasBalance(categoryAccount, transaction))
                    return debitAccountAndSaveTransaction(account, categoryAccount, transaction)

                logger.info(
                    "Insufficient balance to accountId: [{}] with category: [{}]. Checking category in merchant",
                    account.accountId,
                    categoryAccount
                )

                val categoriesMerchant = merchantRepositoryPort.findByName(transaction.merchant).categories

                if (!categoriesMerchant.isNullOrEmpty()) {
                    categoriesMerchant.forEach { categoryMerchant ->
                        if (account.hasBalance(categoryMerchant, transaction))
                            return debitAccountAndSaveTransaction(account, categoryMerchant, transaction)
                    }
                }
                if (account.hasBalanceCash(transaction)) {
                    return debitAccountAndSaveTransaction(account, "CASH", transaction)
                }
                logger.info(
                    "REJECTED transaction, insufficient balance in category [CASH]. return: [{}]",
                    REJECTED
                )
                return REJECTED
            } catch (ex: Exception) {
                logger.error("ERROR process transaction: [{}], message: [{}]", transaction, ex.message)
                ERROR
            }
        }

    private fun debitAccountAndSaveTransaction(
        account: Account,
        category: String,
        transaction: Transaction,
    ): String {
        account.debitBalance(category, transaction)
        accountRepositoryPort.save(account)
        transactionRepositoryPort.save(transaction)
        logger.info(
            "APPROVED transaction. accountId: [{}], category: [{}], return: [{}]",
            account.accountId,
            category,
            APPROVED
        )
        return APPROVED
    }

}