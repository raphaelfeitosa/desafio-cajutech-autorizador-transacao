package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.MerchantRepository
import caju.tech.transactionauthorizer.application.ports.output.MerchantRepositoryPort
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.domain.erros.Errors
import caju.tech.transactionauthorizer.domain.exceptions.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MerchantRepositoryService(
    private val merchantRepository: MerchantRepository,
) : MerchantRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(MerchantRepositoryService::class.java.name)
    }

    @Transactional
    override fun save(merchant: Merchant) {
        logger.info("Starting process to save or update merchant: [{}], in DB.", merchant)
        val merchantEntity = merchantRepository.findByName(merchant.name)

        if (merchantEntity.isPresent) {
            merchantEntity.get().name = merchant.name
            merchantEntity.get().categories = merchant.categories
            merchantEntity.get()
            merchantRepository.save(merchantEntity.get())
            logger.info("Done process to updated merchant: [{}], in DB", merchant)
        } else {
            logger.info("Done process to save merchant: [{}], in DB", merchant)
            merchantRepository.save(merchant.toEntity())
        }
    }

    override fun findByName(name: String): Merchant {
        logger.info("Starting process to find merchant with name: [{}], in DB.", name)
        val merchantEntity = merchantRepository.findByName(name)
            .orElseThrow { NotFoundException(Errors.RESOURCE_NOT_FOUND) }
        logger.info("Done process to find a merchant: [{}], in DB", merchantEntity)
        return merchantEntity.toDomain()
    }

}