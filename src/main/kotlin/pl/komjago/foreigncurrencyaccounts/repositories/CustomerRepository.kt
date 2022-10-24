package pl.komjago.foreigncurrencyaccounts.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.komjago.foreigncurrencyaccounts.domain.Customer

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>