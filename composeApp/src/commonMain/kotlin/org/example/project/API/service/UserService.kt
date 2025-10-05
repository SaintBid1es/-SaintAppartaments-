// commonMain/kotlin/api/UserService.kt
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.example.project.data.model.User

class UserService(private val client: HttpClient) {

    // Теперь используем относительные пути!
    suspend fun getAllUsers(): List<User> {
        return client.get("users").body() // Будет: http://192.168.0.101:8080/api/v1/users
    }

    suspend fun getUserById(id: Int): User {
        return client.get("users/$id").body() // Будет: http://192.168.0.101:8080/api/v1/users/1
    }

    suspend fun createUser(user: User): User {
        return client.post("users") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }

//    suspend fun login(email: String, password: String): AuthResponse {
//        return client.post("auth/login") {
//            contentType(ContentType.Application.Json)
//            setBody(LoginRequest(email, password))
//        }.body()
//    }

}