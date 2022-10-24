package pl.komjago.foreigncurrencyaccounts.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.komjago.foreigncurrencyaccounts.domain.SubAccount

@Repository
interface SubAccountRepository : JpaRepository<SubAccount, Long>