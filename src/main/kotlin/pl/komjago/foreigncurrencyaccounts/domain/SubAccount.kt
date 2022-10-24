package pl.komjago.foreigncurrencyaccounts.domain

import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class SubAccount(
    @Enumerated(EnumType.STRING)
    val currency: Currency,
    @Column(precision = 4, scale = 19)
    val funds: BigDecimal,
    @ManyToOne
    val account: Account? = null,
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0
)
