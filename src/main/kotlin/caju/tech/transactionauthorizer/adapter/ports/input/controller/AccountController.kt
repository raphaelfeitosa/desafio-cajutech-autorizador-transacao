package caju.tech.transactionauthorizer.adapter.ports.input.controller

import caju.tech.transactionauthorizer.adapter.ports.input.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.input.converter.toResponse
import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.response.CreateAccountResponse
import caju.tech.transactionauthorizer.adapter.ports.input.response.UpdateBalanceAccountResponse
import caju.tech.transactionauthorizer.application.ports.input.CreateUseCasePort
import caju.tech.transactionauthorizer.application.ports.input.UpdateBalanceAccountUseCasePort
import caju.tech.transactionauthorizer.`interface`.rest.AccountApi
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val createUseCasePort: CreateUseCasePort,
    private val updateBalanceAccountUseCasePort: UpdateBalanceAccountUseCasePort,
) : AccountApi {

    companion object {
        private val logger = LoggerFactory.getLogger(AccountController::class.java.name)
    }

    @PostMapping
    override fun create(@RequestBody createAccountRequest: CreateAccountRequest): CreateAccountResponse {
        logger.info("Request received to create a new account: [{}].", createAccountRequest)
        val createAccountResponse = createUseCasePort.execute(createAccountRequest.toDomain())
        logger.info("Account created with success!")
        return createAccountResponse.toResponse()
    }

    override fun updateBalance(
        accountId: String,
        updateBalanceAccountRequest: UpdateBalanceAccountRequest,
    ): ResponseEntity<UpdateBalanceAccountResponse> {
        logger.info("Request received to update balance for accountId: [{}].", accountId)
        updateBalanceAccountUseCasePort.execute(updateBalanceAccountRequest.toDomain(accountId))
        logger.info("Balance updated for accountId: [{}] with success!", accountId)
        return ResponseEntity.ok().body(UpdateBalanceAccountResponse("Balance update with success!"))

    }

}