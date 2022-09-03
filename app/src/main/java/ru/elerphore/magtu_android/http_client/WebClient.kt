package ru.elerphore.magtu_android.http_client

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import ru.elerphore.magtu_android.data.ResponseSchoolDay

class WebClient {

    val client = HttpClient(Android) {
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
}

object WC {
    private val wc = WebClient().client

    suspend fun getSchoolDays() = wc.get<ResponseSchoolDay>("http://192.168.0.2:8080/tables/latest/ИСп-19-2")//("https://ivanik.ru/mpk/api/tables/latest")
    suspend fun getGroups() = wc.get<List<String>>("http://192.168.0.2:8080/groups")
}
