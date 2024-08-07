package caju.tech.transactionauthorizer.adapter.ports.input.api.request

import caju.tech.transactionauthorizer.adapter.ports.input.api.request.validation.ValidCategory
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateMerchantRequest(
    @field:NotBlank(message = "name must not be blank")
    @field:Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    val name: String,

    @field:ValidCategory
    val categories: Set<String>?
)