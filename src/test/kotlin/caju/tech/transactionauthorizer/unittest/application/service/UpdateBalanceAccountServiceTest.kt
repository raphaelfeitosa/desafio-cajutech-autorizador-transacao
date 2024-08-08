package caju.tech.transactionauthorizer.unittest.application.service

import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.service.UpdateBalanceAccountService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.BalanceAccount
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class UpdateBalanceAccountServiceTest {
    private lateinit var updateBalanceAccountService: UpdateBalanceAccountService

    private val accountRepositoryPort: AccountRepositoryPort = mock()

    private lateinit var account: Account
    private lateinit var balanceAccount: BalanceAccount


    @BeforeEach
    fun setUp() {
        updateBalanceAccountService = UpdateBalanceAccountService(accountRepositoryPort)
        account = dummyObject()
        balanceAccount = dummyObject()
    }

    @Test
    fun `should update balance`() {
        whenever(accountRepositoryPort.findByAccountId(any())).thenReturn(account)

        updateBalanceAccountService.execute(balanceAccount)

        verify(accountRepositoryPort, times(1)).findByAccountId(any())
    }

}