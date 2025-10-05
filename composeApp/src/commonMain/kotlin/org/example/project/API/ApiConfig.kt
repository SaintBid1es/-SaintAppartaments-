// commonMain/kotlin/config/ApiConfig.kt
object ApiConfig {
    // Для разработки
    const val BASE_URL = "http://192.168.0.101:8080/api/v1/"

    // Или для разных окружений:
    const val DEV_BASE_URL = "http://192.168.0.101:8080/api/v1/"
    const val PROD_BASE_URL = "https://api.myapp.com/api/v1/"

    // Таймауты
    const val TIMEOUT_SECONDS = 30L
}