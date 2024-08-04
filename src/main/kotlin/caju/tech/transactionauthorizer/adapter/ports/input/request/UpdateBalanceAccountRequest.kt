package caju.tech.transactionauthorizer.adapter.ports.input.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits

data class UpdateBalanceAccountRequest(
    @field:DecimalMin(value = "0.01")
    @field:Digits(integer = 4, fraction = 2)
    val food: Double?,

    @field:DecimalMin(value = "0.01")
    @field: Digits(integer = 4, fraction = 2)
    val meal: Double?,

    @field: DecimalMin(value = "0.01")
    @field: Digits(integer = 4, fraction = 2)
    val cash: Double?,
)