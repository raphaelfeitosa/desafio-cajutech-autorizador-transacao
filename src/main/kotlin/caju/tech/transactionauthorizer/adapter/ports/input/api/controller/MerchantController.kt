package caju.tech.transactionauthorizer.adapter.ports.input.api.controller

import caju.tech.transactionauthorizer.adapter.ports.input.api.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.input.api.converter.toResponse
import caju.tech.transactionauthorizer.adapter.ports.input.api.MerchantApi
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateMerchantRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.MerchantIdResponse
import caju.tech.transactionauthorizer.application.ports.input.CreateMerchantUseCasePort
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MerchantController(
    private val createMerchantUseCasePort: CreateMerchantUseCasePort
) : MerchantApi {

    companion object {
        private val logger = LoggerFactory.getLogger(MerchantController::class.java.name)
    }

    override fun create(createMerchantRequest: CreateMerchantRequest): ResponseEntity<MerchantIdResponse> {
        logger.info("Request received to create or update merchant: [{}].", createMerchantRequest)
        val createMerchantResponse = createMerchantUseCasePort.execute(createMerchantRequest.toDomain())
        logger.info("Merchant created or updated with success!")
        return ResponseEntity.ok().body(createMerchantResponse.toResponse())
    }

}