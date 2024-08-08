package caju.tech.transactionauthorizer.unittest.application.service

import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.service.CreateAccountService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CreateAccountServiceTest {


    private lateinit var createAccountService: CreateAccountService

    private val accountRepository: AccountRepositoryPort = mock()

    private lateinit var account: Account


    @BeforeEach
    fun setUp() {
        createAccountService = CreateAccountService(accountRepository)
        account = dummyObject()
    }

    @Test
    fun `should execute create a new account`() {
        createAccountService.execute(account)
        verify(accountRepository, times(1)).save(any())
    }

}