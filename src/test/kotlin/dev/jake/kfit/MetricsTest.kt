package dev.jake.kfit

import dev.jake.kfit.metrics.HealthResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class MetricsTest {

    @Test
    fun `health returns ok as json`() = testApplication {
        application { module() }

        val jsonClient = createClient {
            install(ContentNegotiation) { json() }

        }

        val response = jsonClient.get("/health" )

        assertEquals(HttpStatusCode.OK, response.status)
        val body:HealthResponse = response.body()

        assertEquals("doing ok", body.status)
        assertEquals("1.0-SNAPSHOT", body.version)
        assertNotNull(body.time)

    }
}