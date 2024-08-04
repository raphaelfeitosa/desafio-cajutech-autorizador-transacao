package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.FindAccountByDocumentNumberUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.domain.Account
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FindAccountByDocumentNumberService(
    private val accountRepositoryPort: AccountRepositoryPort,
) : FindAccountByDocumentNumberUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(FindAccountByDocumentNumberService::class.java.name)
    }

    override fun execute(documentNumber: String): Account {
        logger.info("Starting service to find accountId with documentNumber: [{}].", documentNumber)
        val account = accountRepositoryPort.findByDocumentNumber(documentNumber)
        logger.info("Done service to find accountId: [{}] with documentNumber: [{}].", account.accountId, documentNumber)
        return account
    }

}