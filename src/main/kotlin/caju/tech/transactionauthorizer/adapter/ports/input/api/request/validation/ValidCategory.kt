package caju.tech.transactionauthorizer.adapter.ports.input.api.request.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [CategoryValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidCategory(
    val message: String = "Invalid category. Categories must be of type FOOD and MEAL.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

class CategoryValidator : ConstraintValidator<ValidCategory, Set<String>> {

    companion object {
        private const val MEAL = "MEAL"
        private const val FOOD = "FOOD"
    }

    private val validCategories = setOf("MEAL", "FOOD")

    override fun isValid(categories: Set<String>?, context: ConstraintValidatorContext?): Boolean {

        if (categories == null) return true
        return categories.all { it in validCategories }
    }
}