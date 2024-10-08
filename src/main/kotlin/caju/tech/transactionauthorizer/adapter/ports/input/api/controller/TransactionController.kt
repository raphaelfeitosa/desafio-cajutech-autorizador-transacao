package caju.tech.transactionauthorizer.adapter.ports.input.api.controller

import caju.tech.transactionauthorizer.adapter.ports.input.api.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.input.api.converter.toResponse
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.AuthorizerTransactionRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.TransactionResponse
import caju.tech.transactionauthorizer.application.ports.input.AuthorizerTransactionUseCasePort
import caju.tech.transactionauthorizer.adapter.ports.input.api.TransactionApi
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController(
    private val authorizerTransactionUseCasePort: AuthorizerTransactionUseCasePort,
) : TransactionApi {

    companion object {
        private val logger = LoggerFactory.getLogger(TransactionController::class.java.name)
    }

    override fun authorize(@Valid @RequestBody authorizerTransactionRequest: AuthorizerTransactionRequest): ResponseEntity<TransactionResponse> {
        logger.info("Request received to authorize a new transaction: [{}].", authorizerTransactionRequest)
        val authorizerTransactionResponse =
            authorizerTransactionUseCasePort.execute(authorizerTransactionRequest.toDomain())
        logger.info("Done process transaction!")
        return ResponseEntity.status(HttpStatus.OK).body(authorizerTransactionResponse.toResponse())
    }

}