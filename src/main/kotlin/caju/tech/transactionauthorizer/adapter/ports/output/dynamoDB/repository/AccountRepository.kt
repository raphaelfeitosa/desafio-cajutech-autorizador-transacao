package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.AccountEntity
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import java.util.Optional

@EnableScan
interface AccountRepository : CrudRepository<AccountEntity, String> {

    fun findByAccountId(accountId: String): Optional<AccountEntity>
    fun findByDocumentNumber(documentNumber: String): Optional<AccountEntity>
}