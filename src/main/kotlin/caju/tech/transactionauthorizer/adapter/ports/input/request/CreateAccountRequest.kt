package caju.tech.transactionauthorizer.adapter.ports.input.request

import caju.tech.transactionauthorizer.adapter.ports.input.request.validation.ExistsDocumentNumber
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF

data class CreateAccountRequest(
    @field:NotBlank(message = "name must not be blank")
    @field:Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    val name: String,

    @field:ExistsDocumentNumber
    @field:CPF(message = "documentNumber invalid")
    val documentNumber: String,
)