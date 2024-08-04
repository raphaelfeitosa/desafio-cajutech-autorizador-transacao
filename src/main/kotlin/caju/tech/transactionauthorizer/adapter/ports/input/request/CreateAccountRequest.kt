package caju.tech.transactionauthorizer.adapter.ports.input.request

import caju.tech.transactionauthorizer.adapter.ports.input.request.validation.ExistsDocumentNumber
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF

data class CreateAccountRequest(
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    val name: String,

    @field:ExistsDocumentNumber
    @field:CPF
    @field:Pattern(regexp = "\\d{11}", message = "Document number must be exactly 11 digits")
    @field:NotBlank(message = "Document number must not be blank")
    val documentNumber: String,
)