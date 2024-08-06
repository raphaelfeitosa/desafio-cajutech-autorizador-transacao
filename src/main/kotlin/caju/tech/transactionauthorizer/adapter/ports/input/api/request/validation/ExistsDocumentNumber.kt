package caju.tech.transactionauthorizer.adapter.ports.input.api.request.validation

import caju.tech.transactionauthorizer.domain.exceptions.BusinessException
import caju.tech.transactionauthorizer.domain.erros.Errors.DOCUMENT_NUMBER_ALREADY_EXISTS
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ExistsDocumentNumberValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
annotation class ExistsDocumentNumber(
    val message: String = "",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

class ExistsDocumentNumberValidator(
    private val accountRepository: AccountRepository,
) : ConstraintValidator<ExistsDocumentNumber, String> {

    override fun isValid(documentNumber: String?, context: ConstraintValidatorContext?): Boolean {

        if (!documentNumber.isNullOrBlank()) {
            val account = accountRepository.findByDocumentNumber(documentNumber)
            if (account.isPresent) throw BusinessException(DOCUMENT_NUMBER_ALREADY_EXISTS)
        }
        return true
    }
}