package caju.tech.transactionauthorizer.adapter.ports.input.controller

import caju.tech.transactionauthorizer.adapter.ports.input.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.input.converter.toResponse
import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.response.CreateAccountResponse
import caju.tech.transactionauthorizer.application.ports.input.CreateUseCasePort
import caju.tech.transactionauthorizer.`interface`.rest.AccountApi
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val createUseCasePort: CreateUseCasePort
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

}