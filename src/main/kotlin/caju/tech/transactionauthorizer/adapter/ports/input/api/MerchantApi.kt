package caju.tech.transactionauthorizer.adapter.ports.input.api


import caju.tech.transactionauthorizer.adapter.ports.input.api.request.CreateMerchantRequest
import caju.tech.transactionauthorizer.adapter.ports.input.api.response.MerchantIdResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus


@RequestMapping("/api/v1/merchants")
interface MerchantApi {

    @ResponseStatus(CREATED)
    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun create(@Valid @RequestBody createMerchantRequest: CreateMerchantRequest): ResponseEntity<MerchantIdResponse>

}