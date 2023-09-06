package dev.axgr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestClient

@SpringBootApplication
class App {

  @Bean
  fun client(builder: RestClient.Builder) = builder.build()
}

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
