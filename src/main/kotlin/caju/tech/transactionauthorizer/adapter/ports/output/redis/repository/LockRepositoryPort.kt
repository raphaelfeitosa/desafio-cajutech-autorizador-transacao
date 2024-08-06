package caju.tech.transactionauthorizer.adapter.ports.output.redis.repository

import java.util.concurrent.TimeUnit

interface LockRepositoryPort {
    fun lock(key: String, value: String, timeout: Long, unit: TimeUnit): Boolean
    fun unlock(key: String)
}