package caju.tech.transactionauthorizer.unittest.application.service

import caju.tech.transactionauthorizer.application.ports.output.MerchantRepositoryPort
import caju.tech.transactionauthorizer.application.service.CreateMerchantService
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CreateMerchantServiceTest {

    private lateinit var createMerchantService: CreateMerchantService

    private val merchantRepositoryPort: MerchantRepositoryPort = mock()

    private lateinit var merchant: Merchant


    @BeforeEach
    fun setUp() {
        createMerchantService = CreateMerchantService(merchantRepositoryPort)
        merchant = dummyObject()
    }

    @Test
    fun `should execute create a new merchant`() {
        createMerchantService.execute(merchant)
        verify(merchantRepositoryPort, times(1)).save(any())
    }

}