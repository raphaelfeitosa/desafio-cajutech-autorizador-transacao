package caju.tech.transactionauthorizer.domain

import caju.tech.transactionauthorizer.application.ports.output.LockPort

inline fun <T> withLock(
    key: String,
    lockPort: LockPort,
    action: () -> T
): T {
    lockPort.lock(key)
    val result = action()
    lockPort.unlock(key)
    return result
}