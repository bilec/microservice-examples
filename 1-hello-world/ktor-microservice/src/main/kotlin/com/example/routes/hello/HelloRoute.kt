package com.example.routes.hello

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloRouting() {
    route("/api") {
        route("/hello") {
            get {
                call.respondText { "Hello from ktor" }
            }
        }
    }
}