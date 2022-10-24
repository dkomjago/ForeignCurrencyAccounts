package pl.komjago.foreigncurrencyaccounts.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pl.komjago.foreigncurrencyaccounts.domain.Account

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    @Query(
        """select a from Account a join fetch a.subAccounts s
            left join fetch a.customer
            where a.customer.pesel = :pesel"""
    )
    fun findByCustomerPesel(pesel: String): Account?

    fun existsByCustomerPesel(pesel: String): Boolean
}