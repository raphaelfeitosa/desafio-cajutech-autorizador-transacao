package caju.tech.transactionauthorizer.`interface`.rest


import caju.tech.transactionauthorizer.adapter.ports.input.request.AuthorizerTransactionRequest
import caju.tech.transactionauthorizer.adapter.ports.input.response.TransactionResponse
import jakarta.validation.Valid

import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/v1/transactions")
interface TransactionApi {

    @ResponseStatus(OK)
    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun authorize(@Valid @RequestBody authorizerTransactionRequest: AuthorizerTransactionRequest): ResponseEntity<TransactionResponse>

}