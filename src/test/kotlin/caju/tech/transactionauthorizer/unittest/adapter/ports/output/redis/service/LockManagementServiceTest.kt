package caju.tech.transactionauthorizer.unittest.adapter.ports.output.redis.service

import caju.tech.transactionauthorizer.adapter.ports.output.redis.repository.LockRepository
import caju.tech.transactionauthorizer.adapter.ports.output.redis.service.LockManagementService
import caju.tech.transactionauthorizer.domain.exceptions.BusinessException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*
import java.util.concurrent.TimeUnit

class LockManagementServiceTest {

    private lateinit var lockManagementService: LockManagementService
    private val lockRepository: LockRepository = mock()

    private val timeOut: Long = 5
    private val lock: String = "LOCK"
    private lateinit var key: String

    @BeforeEach
    fun setUp() {
        lockManagementService = LockManagementService(lockRepository, timeOut)
        key = UUID.randomUUID().toString()
    }

    @Test
    fun `given a key, should lock it`() {
        whenever(lockRepository.lock(key, lock, timeOut, TimeUnit.MILLISECONDS)).thenReturn(true)

        lockManagementService.lock(key)

        verify(lockRepository).lock(key, lock, timeOut, TimeUnit.MILLISECONDS)
    }

    @Test
    fun `given a key, should throw exception while locking it`() {
        whenever(lockRepository.lock(any(), any(), any(), any())).thenReturn(false)

        assertThrows<BusinessException> { lockManagementService.lock(key) }

        verify(lockRepository).lock(key, lock, timeOut, TimeUnit.MILLISECONDS)
    }

    @Test
    fun `given a key, should unlock it`() {
        doNothing().whenever(lockRepository).unlock(any())

        lockManagementService.unlock(key)

        verify(lockRepository).unlock(key)
    }


    @Test
    fun `given a key, should throw exception while unlocking it`() {
        whenever(lockRepository.unlock(any())).thenThrow(RuntimeException::class.java)

        lockManagementService.unlock(key)

        verify(lockRepository).unlock(key)
    }
}