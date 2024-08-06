package caju.tech.transactionauthorizer.application.ports.output

interface LockPort {
    fun lock(key: String)
    fun unlock(key: String)
}