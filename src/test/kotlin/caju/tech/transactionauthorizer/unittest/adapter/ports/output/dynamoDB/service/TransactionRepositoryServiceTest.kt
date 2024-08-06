package caju.tech.transactionauthorizer.unittest.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.input.exceptions.NotFoundException
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.TransactionRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service.AccountRepositoryService
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service.TransactionRepositoryService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Transaction
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class TransactionRepositoryServiceTest {

    private lateinit var transactionRepositoryService: TransactionRepositoryService

    private val transactionRepository: TransactionRepository = mock()

    private lateinit var transaction: Transaction

    @BeforeEach
    fun setUp() {
        transactionRepositoryService = TransactionRepositoryService(transactionRepository)
        transaction = dummyObject()
    }

    @Test
    fun save() {
        transactionRepositoryService.save(transaction)
        verify(transactionRepository, times(1)).save(any())
    }


}