package dev.axgr

import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.HttpClientErrorException

@SpringBootTest
@WireMockTest(httpPort = 8081)
class AppTests {

  @Autowired
  private lateinit var api: RickAndMorty

  @Test
  fun episodes() {
    assertThrows<HttpClientErrorException.NotFound> {
      api.episodes(1)
    }

    val episodes = api.episodes(2)
    Assertions.assertNotNull(episodes)
    Assertions.assertEquals(20, episodes!!.size)
    Assertions.assertEquals("The Wedding Squanchers", episodes.first().name)
  }
}
