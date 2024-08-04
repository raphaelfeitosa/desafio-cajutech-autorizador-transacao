package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.UpdateBalanceAccountUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.domain.BalanceAccount
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateBalanceAccountService(
    private val accountRepositoryPort: AccountRepositoryPort,
) : UpdateBalanceAccountUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(UpdateBalanceAccountService::class.java.name)
    }

    override fun execute(balanceAccount: BalanceAccount) {
        logger.info("Starting service to update balance for accountId: [{}]", balanceAccount.accountId)
        val account = accountRepositoryPort.findByAccountId(balanceAccount.accountId)
        account.updateBalance(balanceAccount)
        accountRepositoryPort.save(account)
        logger.info("Done service to update balance for accountId: [{}]", balanceAccount.accountId)
    }


}