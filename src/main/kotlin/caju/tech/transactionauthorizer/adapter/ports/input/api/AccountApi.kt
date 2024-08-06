package caju.tech.transactionauthorizer.adapter.ports.input.api


import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.request.UpdateBalanceAccountRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.AccountIdResponse
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.UpdateBalanceAccountResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/v1/accounts")
interface AccountApi {

    @ResponseStatus(OK)
    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun create(@Valid @RequestBody createAccountRequest: CreateAccountRequest): ResponseEntity<AccountIdResponse>

    @ResponseStatus(OK)
    @PatchMapping(
        "/{accountId}/balance",
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]

    )

    fun updateBalance(
        @PathVariable(required = true) @Valid @NotBlank accountId: String,
        @Valid @RequestBody updateBalanceAccountRequest: UpdateBalanceAccountRequest,
    ): ResponseEntity<UpdateBalanceAccountResponse>

    @ResponseStatus(OK)
    @GetMapping(
        "/{documentNumber}",
        produces = [APPLICATION_JSON_VALUE]
    )
    @Valid
    fun findAccountIdByDocumentNumber(
        @PathVariable(required = true) @Valid @NotBlank documentNumber: String,
    ): ResponseEntity<AccountIdResponse>
}