package caju.tech.transactionauthorizer.application.service

import caju.tech.transactionauthorizer.application.ports.input.CreateMerchantUseCasePort
import caju.tech.transactionauthorizer.application.ports.output.MerchantRepositoryPort
import caju.tech.transactionauthorizer.domain.Merchant
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateMerchantService(
    private val merchantRepositoryPort: MerchantRepositoryPort,
) : CreateMerchantUseCasePort {

    companion object {
        private val logger = LoggerFactory.getLogger(CreateMerchantService::class.java.name)
    }

    override fun execute(merchant: Merchant): Merchant {
        logger.info("Starting service to create a new merchant.")
        merchantRepositoryPort.save(merchant)
        logger.info("Done service to create a new merchant.")
        return merchant
    }

}