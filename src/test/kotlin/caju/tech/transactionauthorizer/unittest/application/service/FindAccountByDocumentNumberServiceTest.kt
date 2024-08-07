package caju.tech.transactionauthorizer.unittest.application.service

import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.service.FindAccountByDocumentNumberService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class FindAccountByDocumentNumberServiceTest {

    private lateinit var findAccountByDocumentNumberService: FindAccountByDocumentNumberService

    private val accountRepositoryPort: AccountRepositoryPort = mock()

    private lateinit var account: Account


    @BeforeEach
    fun setUp() {
        findAccountByDocumentNumberService = FindAccountByDocumentNumberService(accountRepositoryPort)
        account = dummyObject()
    }

    @Test
    fun `should execute find account by document Number`() {
        whenever(accountRepositoryPort.findByDocumentNumber(any())).thenReturn(account)

        val result = findAccountByDocumentNumberService.execute(account.documentNumber)

        assertNotNull(result)
        assertEquals(account, result)
        verify(accountRepositoryPort, times(1)).findByDocumentNumber(any())
    }

}