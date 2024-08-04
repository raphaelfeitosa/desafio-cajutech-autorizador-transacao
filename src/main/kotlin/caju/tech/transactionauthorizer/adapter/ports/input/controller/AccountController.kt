package caju.tech.transactionauthorizer.adapter.ports.input.controller

import caju.tech.transactionauthorizer.adapter.ports.input.converter.toDomain
import caju.tech.transactionauthorizer.adapter.ports.input.converter.toResponse
import caju.tech.transactionauthorizer.adapter.ports.input.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.response.AccountIdResponse
import caju.tech.transactionauthorizer.adapter.ports.input.response.UpdateBalanceAccountResponse
import caju.tech.transactionauthorizer.application.ports.input.CreateAccountUseCasePort
import caju.tech.transactionauthorizer.application.ports.input.FindAccountByDocumentNumberUseCasePort
import caju.tech.transactionauthorizer.application.ports.input.UpdateBalanceAccountUseCasePort
import caju.tech.transactionauthorizer.`interface`.rest.AccountApi
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val createAccountUseCasePort: CreateAccountUseCasePort,
    private val updateBalanceAccountUseCasePort: UpdateBalanceAccountUseCasePort,
    private val findAccountByDocumentNumberUseCasePort: FindAccountByDocumentNumberUseCasePort,
) : AccountApi {

    companion object {
        private val logger = LoggerFactory.getLogger(AccountController::class.java.name)
    }

    override fun create(createAccountRequest: CreateAccountRequest): ResponseEntity<AccountIdResponse> {
        logger.info("Request received to create a new account: [{}].", createAccountRequest)
        val createAccountResponse = createAccountUseCasePort.execute(createAccountRequest.toDomain())
        logger.info("Account created with success!")
        return ResponseEntity.ok().body(createAccountResponse.toResponse())
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

    override fun findAccountIdByDocumentNumber(documentNumber: String): ResponseEntity<AccountIdResponse> {
        logger.info("Request received to find accountId with documentNumber: [{}].", documentNumber)
        val accountResponse = findAccountByDocumentNumberUseCasePort.execute(documentNumber)
        logger.info("AccountId found: [{}].", accountResponse.accountId)
        return ResponseEntity.ok().body(accountResponse.toResponse())
    }

}