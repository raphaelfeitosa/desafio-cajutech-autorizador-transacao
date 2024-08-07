package caju.tech.transactionauthorizer.unittest.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.MerchantRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service.MerchantRepositoryService
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.domain.exceptions.NotFoundException
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class MerchantRepositoryServiceTest {

    private lateinit var merchantRepositoryService: MerchantRepositoryService

    private val merchantRepository: MerchantRepository = mock()

    private lateinit var merchant: Merchant

    @BeforeEach
    fun setUp() {
        merchantRepositoryService = MerchantRepositoryService(merchantRepository)
        merchant = dummyObject()
    }

    @Test
    fun save() {
        merchantRepositoryService.save(merchant)
        verify(merchantRepository, times(1)).save(any())
    }

    @Test
    fun findByName() {
        whenever(merchantRepository.findByName(any())).thenReturn(Optional.of(merchant.toEntity()))
        merchantRepositoryService.findByName(merchant.name)
        verify(merchantRepository, times(1)).findByName(any())
    }


    @Test
    fun `findByName return throw a NotFoundException`() {
        assertThrows<NotFoundException> {
            merchantRepositoryService.findByName(merchant.name)
        }
        verify(merchantRepository, times(1)).findByName(any())
    }
}