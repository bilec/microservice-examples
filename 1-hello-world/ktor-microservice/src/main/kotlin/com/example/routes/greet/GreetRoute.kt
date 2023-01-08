package com.example.routes.greet

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.greetRouting() {
    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    val saying = environment?.config?.propertyOrNull("greet.saying")?.getString().toString()
    val backendServiceHost = environment?.config?.propertyOrNull("greet.backendServiceHost")?.getString() ?: throw(Exception("property greet.backendServiceHost is null"))
    val backendServicePort = environment?.config?.propertyOrNull("greet.backendServicePort")?.getString() ?: throw(Exception("property greet.backendServicePort is null"))

    route("/api") {
        route("/greeting") {
            get {
                val httpResponse = client.get {
                    url {
                        url.protocol = URLProtocol.HTTP
                        url.host = backendServiceHost
                        url.port = backendServicePort.toInt()
                        path("/backend")
                        parameter("greeting", saying)
                    }
                }
                val backendResponse = httpResponse.body<BackendResponse>()
                call.respondText { "${backendResponse.greeting} at host: ${backendResponse.ip}" }
            }
        }
    }
}
