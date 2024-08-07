package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.Merchant

interface FindMerchantByNameUseCasePort {
    fun execute(name: String): Merchant
}