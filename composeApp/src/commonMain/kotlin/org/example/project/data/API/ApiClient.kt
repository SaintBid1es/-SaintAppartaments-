// commonMain/kotlin/api/ApiClient.kt
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiClient {
    val httpClient = HttpClient {
        // Базовый URL
        defaultRequest {
            url(ApiConfig.BASE_URL)
        }


        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }

    fun close() {
        httpClient.close()
    }
}