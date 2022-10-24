package pl.komjago.foreigncurrencyaccounts.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Account(
    @OneToOne
    val customer: Customer,
    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], orphanRemoval = true)
    val subAccounts: List<SubAccount> = emptyList(),
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0
)
