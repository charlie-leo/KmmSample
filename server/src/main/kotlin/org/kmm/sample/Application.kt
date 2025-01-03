package org.kmm.sample

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.kmm.sample.model.ExpenseModel
import org.kmm.sample.storage.SingleTon


fun main() {
    embeddedServer(
        Netty, port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
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
        exception<Throwable>{ call , cause ->
            call.respond(HttpStatusCode.InternalServerError, "Something went wrong : ${cause.message}")
        }

    }

    routing {
        get("/") {
            try {
                call.respondText("Server Is Up.")
            }catch (e: Exception){
                println(e)
            }
        }
        post("/save") {
            try {
                val tempStorage = SingleTon.storageClient
                val response = call.receive<ExpenseModel>()

                tempStorage.saveExpense(response)

                call.respond(
                    HttpStatusCode.OK,
                    tempStorage.fetchData()
                )
            }catch (e: Exception){
                println(e)
            }
        }
        get("/findById/{id}") {
            try {

                val id = call.parameters["id"]?.toInt() ?: 0
                val tempStorage = SingleTon.storageClient

                if (tempStorage.fetchData().size > id){
                    val response = tempStorage.findById(id)
                    call.respond(
                        HttpStatusCode.OK,
                        response
                    )
                } else {
                    call.respond(
                        HttpStatusCode.OK,
                        "Item Not Found."
                    )
                }

            }catch (e: Exception){
                println(e)
            }
        }

    }


}













