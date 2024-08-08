package caju.tech.transactionauthorizer.application.ports.input

import caju.tech.transactionauthorizer.domain.Merchant

interface CreateMerchantUseCasePort {
    fun execute(merchant: Merchant): Merchant
}