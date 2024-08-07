package caju.tech.transactionauthorizer.adapter.ports.input.api.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.UUID

data class AuthorizerTransactionRequest(
    @field:UUID(message = "accountId must be an UUID")
    val accountId: String,

    @field:DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
    @field:Digits(integer = 6, fraction = 2, message = "numeric value outside the limit. integer: 6 digits. fraction: 2 digits")
    val amount: Double,

    @field:NotBlank(message = "merchant cannot be blank")
    @Pattern(regexp = "^\\d{4}$", message = "mcc must contain 4 numbers")
    val merchant: String,

    @field:NotBlank(message = "mcc cannot be blank")
    val mcc: String,
)