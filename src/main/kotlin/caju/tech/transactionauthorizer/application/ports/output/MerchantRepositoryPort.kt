package caju.tech.transactionauthorizer.application.ports.output

import caju.tech.transactionauthorizer.domain.Merchant

interface MerchantRepositoryPort {
    fun save(merchant: Merchant)
    fun findByName(name: String): Merchant
}