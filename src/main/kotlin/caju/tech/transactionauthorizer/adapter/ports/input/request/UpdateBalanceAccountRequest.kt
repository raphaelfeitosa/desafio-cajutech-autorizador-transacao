package caju.tech.transactionauthorizer.adapter.ports.input.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits

data class UpdateBalanceAccountRequest(
    @field:DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
    @field:Digits(
        integer = 6,
        fraction = 2,
        message = "numeric value outside the limit. integer: 6 digits. fraction: 2 digits"
    )
    val food: Double?,

    @field:DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
    @field:Digits(
        integer = 6,
        fraction = 2,
        message = "numeric value outside the limit. integer: 6 digits. fraction: 2 digits"
    )
    val meal: Double?,

    @field:DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
    @field: Digits(
        integer = 6,
        fraction = 2,
        message = "numeric value outside the limit. integer: 6 digits. fraction: 2 digits"
    )
    val cash: Double?,
)