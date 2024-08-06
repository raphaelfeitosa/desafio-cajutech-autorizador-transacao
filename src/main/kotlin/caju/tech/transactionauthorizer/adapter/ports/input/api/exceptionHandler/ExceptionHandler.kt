package caju.tech.transactionauthorizer.adapter.ports.input.api.exceptionHandler

import caju.tech.transactionauthorizer.domain.erros.Errors
import caju.tech.transactionauthorizer.domain.exceptions.BusinessException
import caju.tech.transactionauthorizer.domain.exceptions.NotFoundException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.HandlerMethodValidationException

@RestControllerAdvice
class ExceptionHandler(
    private val messageSource: MessageSource,
) {

    @ExceptionHandler(
        MethodArgumentNotValidException::class,
        HandlerMethodValidationException::class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(
        exception: Exception,
        request: WebRequest,
    ): ErrorMessageResponse {
        val errorMessage = hashMapOf<String, String?>()

        val fieldErrors = when (exception) {
            is MethodArgumentNotValidException -> exception.allErrors
            is HandlerMethodValidationException -> exception.allErrors
            else -> emptyList()
        }
        fieldErrors.forEach {
            errorMessage[it.defaultMessage.toString()] = messageSource.getMessage(it, LocaleContextHolder.getLocale())
        }
        return ErrorMessageResponse(
            code = HttpStatus.BAD_REQUEST.name,
            message = Errors.METHOD_ARGUMENT_NOT_VALID,
            path = request.getDescription(false),
            errors = errorMessage
        )
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(
        exception: NotFoundException,
        request: WebRequest,
    ): ErrorMessageResponse {
        return ErrorMessageResponse(
            code = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.getDescription(false)
        )
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleBusinessException(
        exception: BusinessException,
        request: WebRequest,
    ): ErrorMessageResponse {
        return ErrorMessageResponse(
            code = HttpStatus.UNPROCESSABLE_ENTITY.name,
            message = exception.message,
            path = request.getDescription(false)
        )
    }

}