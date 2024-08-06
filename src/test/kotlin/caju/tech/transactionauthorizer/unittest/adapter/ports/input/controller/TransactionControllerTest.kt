package caju.tech.transactionauthorizer.unittest.adapter.ports.input.controller

import caju.tech.transactionauthorizer.adapter.ports.input.api.controller.TransactionController
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.AuthorizerTransactionRequest
import caju.tech.transactionauthorizer.application.ports.input.AuthorizerTransactionUseCasePort
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TransactionControllerTest {

    private lateinit var transactionController: TransactionController

    private val authorizerTransactionUseCasePort: AuthorizerTransactionUseCasePort = mock()


    @BeforeEach
    fun setUp() {
        transactionController = TransactionController(
            authorizerTransactionUseCasePort
        )
    }

    @Test

    fun authorize() {
        val authorizerTransactionRequest = dummyObject<AuthorizerTransactionRequest>()

        whenever(authorizerTransactionUseCasePort.execute(any())).thenReturn("00")

        val authorizerTransactionResponse = transactionController.authorize(authorizerTransactionRequest)

        assertNotNull(authorizerTransactionResponse)
        assertEquals("00", authorizerTransactionResponse.body!!.code)
        verify(authorizerTransactionUseCasePort, times(1)).execute(any())
    }
}