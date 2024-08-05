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
            accountRepositoryPort.findByAccountId(transaction.accountId).let { account ->
                account.getCategoryBalance(transaction.mcc).let { category ->
                    account.hasBalance(category, transaction).let { hasBalance ->
                        when (hasBalance) {
                            true -> debitAccountAndSaveTransaction(account, category, transaction)
                            false -> {
                                logger.info(
                                    "Insufficient balance to accountId: [{}] with category: [{}]. Checking balance in category: CASH",
                                    account.accountId,
                                    category
                                )
                                if (account.hasBalance(category = "CASH", transaction)) {
                                    debitAccountAndSaveTransaction(account, "CASH", transaction)
                                } else {
                                    logger.info("REJECTED transaction, insufficient balance in category [CASH]. return: [{}]", REJECTED)
                                    REJECTED
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            logger.error("ERROR process transaction: [{}], message: [{}]", transaction, ex.message)
            ERROR
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