package pl.komjago.foreigncurrencyaccounts.domain

import org.hibernate.validator.constraints.pl.PESEL
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Customer(
    @PESEL
    val pesel: String,
    val name: String,
    val surname: String,
    val birthdate: LocalDate,
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0
)
