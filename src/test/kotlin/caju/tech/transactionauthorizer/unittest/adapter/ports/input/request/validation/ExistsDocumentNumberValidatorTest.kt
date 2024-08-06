package caju.tech.transactionauthorizer.unittest.adapter.ports.input.request.validation

import caju.tech.transactionauthorizer.adapter.ports.input.api.request.validation.ExistsDocumentNumberValidator
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository.AccountRepository
import caju.tech.transactionauthorizer.domain.erros.Errors.DOCUMENT_NUMBER_ALREADY_EXISTS
import caju.tech.transactionauthorizer.domain.exceptions.BusinessException
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.test.assertEquals

class ExistsDocumentNumberValidatorTest {

    private val accountRepository: AccountRepository = mock()

    private lateinit var existsDocumentNumberValidator: ExistsDocumentNumberValidator

    private lateinit var accountEntity: AccountEntity
    private lateinit var documentNumber: String

    @BeforeEach
    fun setUp() {
        existsDocumentNumberValidator = ExistsDocumentNumberValidator(accountRepository)
        accountEntity = dummyObject()
        documentNumber = dummyObject()
    }

    @Test
    fun `should throw BusinessException if document number already exists`() {
        whenever(accountRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(accountEntity))
        val exception = assertThrows<BusinessException> {
            existsDocumentNumberValidator.isValid(documentNumber, any())
        }

        assertEquals(DOCUMENT_NUMBER_ALREADY_EXISTS, exception.message)
    }

    @Test
    fun `should return true if document number is null or blank`() {
        assertTrue(existsDocumentNumberValidator.isValid(null, any()))
        assertTrue(existsDocumentNumberValidator.isValid("", any()))
    }

    @Test
    fun `should return true if document number does not exist`() {
        whenever(accountRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.empty())
        assertTrue(existsDocumentNumberValidator.isValid(documentNumber, any()))
    }
}