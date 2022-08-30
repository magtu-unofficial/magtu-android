package ru.elerphore.magtu_android.http_client

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import ru.elerphore.magtu_android.data.ResponseSchoolDay

class WebClient {

    private val client = HttpClient(Android) {
        expectSuccess = true
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }
    companion object {
        private val json = kotlinx.serialization.json.Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }
    suspend fun getSchoolDays(): ResponseSchoolDay = client.get<ResponseSchoolDay>("https://ivanik.ru/mpk/api/tables/latest")
}

object WC {
    val wc = WebClient()
}