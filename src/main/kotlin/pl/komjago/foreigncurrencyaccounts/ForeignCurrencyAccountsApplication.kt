package pl.komjago.foreigncurrencyaccounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("pl.komjago.foreigncurrencyaccounts")
class ForeignCurrencyAccountsApplication

fun main(args: Array<String>) {
    runApplication<ForeignCurrencyAccountsApplication>(*args)
}
