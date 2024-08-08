package caju.tech.transactionauthorizer.unittest.application.service

import caju.tech.transactionauthorizer.application.ports.output.AccountRepositoryPort
import caju.tech.transactionauthorizer.application.ports.output.LockPort
import caju.tech.transactionauthorizer.application.ports.output.MerchantRepositoryPort
import caju.tech.transactionauthorizer.application.ports.output.TransactionRepositoryPort
import caju.tech.transactionauthorizer.application.service.AuthorizerTransactionService
import caju.tech.transactionauthorizer.domain.Account
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.domain.Transaction
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class AuthorizerTransactionServiceTest {

    private lateinit var authorizerTransactionService: AuthorizerTransactionService

    private val transactionRepositoryPort: TransactionRepositoryPort = mock()
    private val accountRepositoryPort: AccountRepositoryPort = mock()
    private val merchantRepositoryPort: MerchantRepositoryPort = mock()
    private val lockPort: LockPort = mock()

    private lateinit var account: Account
    private lateinit var transaction: Transaction
    private lateinit var merchant: Merchant

    private val APPROVED = "00"
    private val REJECTED = "51"
    private val ERROR = "07"

    @BeforeEach
    fun setUp() {
        authorizerTransactionService = AuthorizerTransactionService(
            transactionRepositoryPort,
            accountRepositoryPort,
            merchantRepositoryPort,
            lockPort
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["5411", "5412"])
    fun `should approve transaction with sufficient balance in account category FOOD `(mcc: String) {

        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 700.00)
        account = dummyObject<Account>().copy(foodAmount = 1000.00)

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort).save(account)
        verify(transactionRepositoryPort).save(transaction)
        assertEquals(APPROVED, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5811", "5812"])
    fun `should approve transaction with sufficient balance in account category MEAL `(mcc: String) {
        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 700.00)
        account = dummyObject<Account>().copy(mealAmount = 1000.00)

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort).save(account)
        verify(transactionRepositoryPort).save(transaction)
        assertEquals(APPROVED, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5811", "5812"])
    fun `should approve transaction with insufficient balance in categoryMEAL and sufficient balance in categoryMerchant FOOD`(
        mcc: String,
    ) {
        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 1000.00, merchant = "Merchant")
        account = dummyObject<Account>().copy(mealAmount = 700.00, foodAmount = 1500.00)
        merchant = dummyObject<Merchant>().copy(name = "Merchant", categories = listOf("FOOD"))

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)
        whenever(merchantRepositoryPort.findByName(transaction.merchant)).thenReturn(merchant)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort).save(account)
        verify(transactionRepositoryPort).save(transaction)
        verify(merchantRepositoryPort).findByName(merchant.name)
        assertEquals(APPROVED, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5411", "5412"])
    fun `should approve transaction with insufficient balance in categoryFOOD and sufficient balance in categoryMerchant MEAL`(
        mcc: String,
    ) {
        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 1000.00, merchant = "Merchant")
        account = dummyObject<Account>().copy(mealAmount = 1500.00, foodAmount = 700.00)
        merchant = dummyObject<Merchant>().copy(name = "Merchant", categories = listOf("MEAL"))

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)
        whenever(merchantRepositoryPort.findByName(transaction.merchant)).thenReturn(merchant)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort).save(account)
        verify(transactionRepositoryPort).save(transaction)
        verify(merchantRepositoryPort).findByName(merchant.name)
        assertEquals(APPROVED, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5411", "5412", "5811", "5812"])
    fun `should approve transaction with insufficient balance in categoryFOOD, categoryMEAL and sufficient balance in categoryMerchant CASH`(
        mcc: String,
    ) {
        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 1000.00, merchant = "Merchant")
        account = dummyObject<Account>().copy(mealAmount = 700.00, foodAmount = 700.00, cashAmount = 1000.0)
        merchant = dummyObject<Merchant>().copy(name = "Merchant", categories = listOf("MEAL", "FOOD"))

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)
        whenever(merchantRepositoryPort.findByName(transaction.merchant)).thenReturn(merchant)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort).save(account)
        verify(transactionRepositoryPort).save(transaction)
        verify(merchantRepositoryPort).findByName(merchant.name)
        assertEquals(APPROVED, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5411", "5412", "5811", "5812"])
    fun `should rejected transaction with insufficient balance in categoryFOOD, categoryMEAL and categoryCASH`(
        mcc: String,
    ) {
        transaction = dummyObject<Transaction>().copy(mcc = mcc, amount = 1000.00, merchant = "Merchant")
        account = dummyObject<Account>().copy(mealAmount = 700.00, foodAmount = 700.00, cashAmount = 700.0)
        merchant = dummyObject<Merchant>().copy(name = "Merchant", categories = listOf("MEAL", "FOOD"))

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenReturn(account)
        whenever(merchantRepositoryPort.findByName(transaction.merchant)).thenReturn(merchant)

        val result = authorizerTransactionService.execute(transaction)

        verify(accountRepositoryPort, times(0)).save(account)
        verify(transactionRepositoryPort, times(0)).save(transaction)
        verify(merchantRepositoryPort, times(1)).findByName(merchant.name)
        assertEquals(REJECTED, result)
    }

    @Test
    fun `should return error when exception account repository`() {
        val transaction = dummyObject<Transaction>()

        whenever(accountRepositoryPort.findByAccountId(transaction.accountId)).thenThrow(RuntimeException::class.java)

        val result = authorizerTransactionService.execute(transaction)

        assertEquals(ERROR, result)
    }

    @Test
    fun `should return error when exception merchant repository`() {
        val transaction = dummyObject<Transaction>()

        whenever(merchantRepositoryPort.findByName(transaction.merchant)).thenThrow(RuntimeException::class.java)

        val result = authorizerTransactionService.execute(transaction)

        assertEquals(ERROR, result)
    }
}
