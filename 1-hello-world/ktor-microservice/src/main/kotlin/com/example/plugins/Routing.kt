package com.example.plugins

import com.example.routes.greet.greetRouting
import com.example.routes.hello.helloRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        helloRouting()
        greetRouting()
    }
}
