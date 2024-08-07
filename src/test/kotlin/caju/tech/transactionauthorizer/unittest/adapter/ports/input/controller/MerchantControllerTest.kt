package caju.tech.transactionauthorizer.unittest.adapter.ports.input.controller


import caju.tech.transactionauthorizer.adapter.ports.input.api.controller.MerchantController
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateMerchantRequest
import caju.tech.transactionauthorizer.application.ports.input.CreateMerchantUseCasePort
import caju.tech.transactionauthorizer.domain.Merchant
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MerchantControllerTest {

    private lateinit var merchantController: MerchantController

    private val createMerchantUseCasePort: CreateMerchantUseCasePort = mock()

    private lateinit var merchant: Merchant
    private val merchantId: UUID = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        merchantController = MerchantController(
            createMerchantUseCasePort
        )

        merchant = dummyObject<Merchant>().copy(merchantId = merchantId)
    }


    @Test
    fun create() {
        val createMerchantRequest = dummyObject<CreateMerchantRequest>()
        whenever(createMerchantUseCasePort.execute(any())).thenReturn(merchant)

        val merchantResponse = merchantController.create(createMerchantRequest)

        assertNotNull(merchantResponse)
        assertEquals(merchant.merchantId, merchantResponse.body!!.merchantId)
        verify(createMerchantUseCasePort, times(1)).execute(any())
    }

}