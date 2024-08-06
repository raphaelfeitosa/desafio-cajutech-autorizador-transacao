package caju.tech.transactionauthorizer.adapter.ports.output.redis.repository

import org.slf4j.LoggerFactory
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class LockRepository(
    private val redisTemplate: RedisTemplate<String, Any>,
) : LockRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(LockRepository::class.java.name)
    }

    override fun lock(key: String, value: String, timeout: Long, unit: TimeUnit): Boolean =
        try {
            redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit) ?: false
        } catch (exception: RedisConnectionFailureException) {
            logger.error(
                "Timeout with redis to lock value: [{}] with key: [{}].",
                value,
                key,
                exception
            )
            true
        }

    override fun unlock(key: String) {
        try {
            redisTemplate.delete(key)
        } catch (exception: RedisConnectionFailureException) {
            logger.error(
                "Timeout with redis to unlock key: [{}].",
                key
            )
        }
    }

}