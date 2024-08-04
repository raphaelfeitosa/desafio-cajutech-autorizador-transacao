package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.CreateAccountUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateAccountAccountService(
    private val accountRepositoryPort: AccountRepositoryPort
) : CreateAccountUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(CreateAccountAccountService::class.java.name)
    }

    override fun execute(account: Account): Account {
        logger.info("Starting service to create a new account.")
        accountRepositoryPort.save(account)
        logger.info("Done service to create a new account.")
        return account
    }

}