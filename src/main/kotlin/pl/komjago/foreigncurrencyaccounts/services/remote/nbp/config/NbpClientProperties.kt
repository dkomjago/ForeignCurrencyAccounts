package pl.komjago.foreigncurrencyaccounts.services.remote.nbp.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConfigurationProperties(prefix = "remote.nbp")
@ConstructorBinding
data class NbpClientProperties(
   val baseUrl: String,
   val timeout: Duration
)