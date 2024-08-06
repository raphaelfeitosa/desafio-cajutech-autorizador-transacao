package caju.tech.transactionauthorizer.unittest.adapter.ports.output.dynamoDB.service

import caju.tech.transactionauthorizer.adapter.ports.input.exceptions.NotFoundException
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.converter.toEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.service.AccountRepositoryService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*

class AccountRepositoryServiceTest {

    private lateinit var accountRepositoryService: AccountRepositoryService

    private val accountRepository: AccountRepository = mock()

    private lateinit var account: Account

    @BeforeEach
    fun setUp() {
        accountRepositoryService = AccountRepositoryService(accountRepository)
        account = dummyObject()
    }

    @Test
    fun save() {
        accountRepositoryService.save(account)
        verify(accountRepository, times(1)).save(any())
    }

    @Test
    fun findByAccountId() {
        whenever(accountRepository.findByAccountId(any())).thenReturn(Optional.of(account.toEntity()))
        accountRepositoryService.findByAccountId(account.accountId.toString())
        verify(accountRepository, times(1)).findByAccountId(any())
    }

    @Test
    fun findByDocumentNumber() {
        whenever(accountRepository.findByDocumentNumber(any())).thenReturn(Optional.of(account.toEntity()))
        accountRepositoryService.findByDocumentNumber(account.documentNumber)
        verify(accountRepository, times(1)).findByDocumentNumber(any())
    }

    @Test
    fun `findByAccountId return throw a NotFoundException`() {
        assertThrows<NotFoundException> {
            accountRepositoryService.findByAccountId(account.accountId.toString())
        }
        verify(accountRepository, times(1)).findByAccountId(any())
    }

    @Test
    fun `findByDocumentNumber return throw a NotFoundException`() {
        assertThrows<NotFoundException> {
            accountRepositoryService.findByDocumentNumber(account.documentNumber)
        }
        verify(accountRepository, times(1)).findByDocumentNumber(any())
    }

}