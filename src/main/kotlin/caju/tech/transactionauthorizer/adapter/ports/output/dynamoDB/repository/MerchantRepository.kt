package caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.repository

import caju.tech.transactionauthorizer.adapter.ports.output.dynamoDB.entity.MerchantEntity
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import java.util.*

@EnableScan
interface MerchantRepository : CrudRepository<MerchantEntity, String> {

    fun findByName(name: String): Optional<MerchantEntity>
}