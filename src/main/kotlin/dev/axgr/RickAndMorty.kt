package dev.axgr

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@ConfigurationProperties("api")
data class RickAndMortyProperties(val url: String)

@Component
@EnableConfigurationProperties(RickAndMortyProperties::class)
class RickAndMorty(client: RestClient, props: RickAndMortyProperties) {

  private val http = client.mutate().baseUrl(props.url).build()

  fun episodes(page: Int = 1): List<Episode>? {
    return http
      .get()
      .uri {
        it
          .pathSegment("episode")
          .queryParam("page", page)
          .build()
      }
      .retrieve()
      .body(Episodes::class.java)
      ?.results
  }


}

data class Episode(val id: Int, val name: String, val episode: String)
data class Episodes(val results: List<Episode>)
