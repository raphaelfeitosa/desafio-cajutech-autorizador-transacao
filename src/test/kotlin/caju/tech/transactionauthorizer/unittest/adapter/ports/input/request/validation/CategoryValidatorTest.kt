package caju.tech.transactionauthorizer.unittest.adapter.ports.input.request.validation

import caju.tech.transactionauthorizer.adapter.ports.input.api.request.validation.CategoryValidator
import caju.tech.transactionauthorizer.helper.dummyObject
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class CategoryValidatorTest {

    private lateinit var categoryValidator: CategoryValidator

    private lateinit var categories: Set<String>

    @BeforeEach
    fun setUp() {
        categoryValidator = CategoryValidator()
        categories = dummyObject()
    }

    @Test
    fun `should return true for valid categories`() {
        val validCategories = setOf("FOOD", "MEAL")
        assertTrue(categoryValidator.isValid(validCategories, mock()))
    }

    @Test
    fun `should return true for empty categories`() {
        val emptyCategories = emptySet<String>()
        assertTrue(categoryValidator.isValid(emptyCategories, mock()))
    }

    @Test
    fun `should return true for null categories`() {
        assertTrue(categoryValidator.isValid(null, mock()))
    }

    @Test
    fun `should return false for invalid categories`() {
        val invalidCategories = setOf("FOOD", "INVALID")
        assertFalse(categoryValidator.isValid(invalidCategories, mock()))
    }

}