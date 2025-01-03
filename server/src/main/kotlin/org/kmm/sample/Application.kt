package org.kmm.sample

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.contentType
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.contentType
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.kmm.sample.model.ExpenseModel

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module,
        watchPaths = listOf("classes", "resources"),
    )
        .start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation){
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, "Something went wrong: ${cause.message}")
        }
    }


    routing {
        get("/") {
            try {
                call.respondText("Server Is Up.")
            }catch (e : Exception){
                println(e)
            }
        }

        post("/save"){

            try {
                println("Content Type - " + call.request.contentType())

                val tempStorage = SingleTon.storageClient
                val response = call.receive<ExpenseModel>()

                tempStorage.saveExpense(response)

                call.respond(
                    HttpStatusCode.OK,
                    tempStorage.fetchData()
                )
            }catch (e : Exception) {
                println(e)
            }
        }

        get("/getAllExpense") {
            val tempStorage = SingleTon.storageClient
            call.respond(
                HttpStatusCode.OK,
                message = tempStorage.fetchData()
            )
        }
        get("/findById/{id}") {

            try {
                val id = call.parameters["id"]?.toInt() ?: 0
                val tempStorage = SingleTon.storageClient

                if (tempStorage.fetchData().size > id) {
                    val response = tempStorage.findById(id)
                    call.respond(
                        HttpStatusCode.OK,
                        message = response
                    )
                } else {
                    call.respond(
                        HttpStatusCode.OK,
                        message = "Item Not Found."
                    )
                }
            } catch (e : Exception){
                println(e)
            }

        }
    }
}
