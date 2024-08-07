package caju.tech.transactionauthorizer.unittest.adapter.ports.input.controller


import caju.tech.transactionauthorizer.adapter.ports.input.api.controller.AccountController
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.application.ports.input.CreateAccountUseCasePort
import caju.tech.transactionauthorizer.application.ports.input.FindAccountByDocumentNumberUseCasePort
import caju.tech.transactionauthorizer.application.ports.input.UpdateBalanceAccountUseCasePort
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AccountControllerTest {

    private lateinit var accountController: AccountController

    private val createAccountUseCasePort: CreateAccountUseCasePort = mock()
    private val updateBalanceAccountUseCasePort: UpdateBalanceAccountUseCasePort = mock()
    private val findAccountByDocumentNumberUseCasePort: FindAccountByDocumentNumberUseCasePort = mock()

    private lateinit var account: Account
    private val accountId: UUID = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        accountController = AccountController(
            createAccountUseCasePort,
            updateBalanceAccountUseCasePort,
            findAccountByDocumentNumberUseCasePort
        )

        account = dummyObject<Account>().copy(accountId = accountId)
    }


    @Test
    fun create() {
        val createAccountRequest = dummyObject<CreateAccountRequest>()

        whenever(createAccountUseCasePort.execute(any())).thenReturn(account)

        val accountResponse = accountController.create(createAccountRequest)

        assertNotNull(accountResponse)
        assertEquals(account.accountId, accountResponse.body!!.accountId)
        verify(createAccountUseCasePort, times(1)).execute(any())
    }

    @Test
    fun updateBalance() {
        val updateBalanceAccountRequest =
            dummyObject<UpdateBalanceAccountRequest>().copy(food = 100.00, meal = 100.00, cash = 100.00)

        val response = accountController.updateBalance(account.accountId.toString(), updateBalanceAccountRequest)

        assertNotNull(response)
        assertEquals("Balance update with success!", response.body!!.message)
        verify(updateBalanceAccountUseCasePort, times(1)).execute(any())
    }

    @Test
    fun findAccountIdByDocumentNumber() {
        whenever(findAccountByDocumentNumberUseCasePort.execute(any())).thenReturn(account)
        val accountResponse = accountController.findAccountIdByDocumentNumber(dummyObject())

        assertNotNull(accountResponse)
        verify(findAccountByDocumentNumberUseCasePort).execute(any())
    }
}