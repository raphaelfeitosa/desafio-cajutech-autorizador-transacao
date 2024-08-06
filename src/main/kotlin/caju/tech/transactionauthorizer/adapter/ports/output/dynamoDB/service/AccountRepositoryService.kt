package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.input.exceptions.NotFoundException
import caju.tech.transactionauthorizer.adapter.ports.input.exceptions.errors.Errors
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountRepositoryService(
    private val accountRepository: AccountRepository,
) : AccountRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(AccountRepositoryService::class.java.name)
    }

    @Transactional
    override fun save(account: Account) {
        logger.info("Starting process to save account: [{}], in DB.", account)
        val accountEntity = account.toEntity()
        accountRepository.save(accountEntity)
        logger.info("Done process to save account: [{}], in DB", account)
    }

    override fun findByAccountId(accountId: String): Account {
        logger.info("Starting process to find account with accountId: [{}], in DB.", accountId)
        val accountEntity = accountRepository.findByAccountId(accountId)
            .orElseThrow { NotFoundException(Errors.RESOURCE_NOT_FOUND) }
        logger.info("Done process to find a account: [{}], in DB", accountEntity)
        return accountEntity.toDomain()
    }

    override fun findByDocumentNumber(documentNumber: String): Account {
        logger.info("Starting process to find accountId with documentNumber: [{}], in DB.", documentNumber)
        val accountEntity = accountRepository.findByDocumentNumber(documentNumber)
            .orElseThrow { NotFoundException(Errors.RESOURCE_NOT_FOUND) }
        logger.info(
            "Done process to find accountId: [{}], with documentNumber: [{}], in DB.",
            accountEntity.accountId,
            documentNumber
        )
        return accountEntity.toDomain()
    }

}