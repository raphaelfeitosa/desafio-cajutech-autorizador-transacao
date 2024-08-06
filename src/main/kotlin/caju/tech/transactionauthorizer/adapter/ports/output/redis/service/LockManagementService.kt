package caju.tech.transactionauthorizer.adapter.ports.output.redis.service

import caju.tech.transactionauthorizer.domain.exceptions.BusinessException
import caju.tech.transactionauthorizer.domain.erros.Errors
import caju.tech.transactionauthorizer.adapter.ports.output.redis.repository.LockRepository
import caju.tech.transactionauthorizer.application.ports.output.LockPort
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit.MILLISECONDS


@Service
class LockManagementService(
    private val lockRepository: LockRepository,
    @Value("\${lock.timeout.milliseconds:5000}")
    private val timeout: Long,
) : LockPort {

    companion object {
        private val logger = LoggerFactory.getLogger(LockManagementService::class.java.name)
        private const val LOCK = "LOCK"
    }

    override fun lock(key: String) {
        logger.info("Starting process to lock the resource [{}]", key)
        val result = lockRepository.lock(key, LOCK, timeout, MILLISECONDS)
        if (!result) {
            logger.info("Resource [{}] already locked", key)
            throw BusinessException(Errors.RESOURCE_ALREADY_LOCKED)
        }
        logger.info("Done process to lock the resource [{}]", key)
    }

    override fun unlock(key: String) {
        try {
            logger.info("Starting process to unlock the resource [{}]", key)
            lockRepository.unlock(key)
            logger.info("Done process to unlock the resource [{}]", key)
        } catch (ex: Exception) {
            logger.info("Error to unlock the key [{}]", key)
        }
    }


}