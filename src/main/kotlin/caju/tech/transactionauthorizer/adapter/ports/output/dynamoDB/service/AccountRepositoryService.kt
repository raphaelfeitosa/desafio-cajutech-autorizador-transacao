package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountRepositoryService(
    private val accountRepository: AccountRepository,
) : AccountRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(AccountRepositoryService::class.java.name)
    }

    @Transactional
    override fun save(account: Account) {
        logger.info("Starting process to save a new account: [{}], in DB.", account)
        val accountEntity = account.toEntity()
        accountRepository.save(accountEntity)
        logger.info("Done process to save a new account: [{}], in DB", account)
    }

}