package org.kmm.sample

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

/**
 * Created by Charles Raj I on 28/12/24
 * @project KmmSample
 * @author Charles Raj
 */
class ApiService(private val client : HttpClient) {


    suspend fun getUserList(callBack : (value : List<String>) -> Unit){

        val response = client.get("http://10.0.2.2:8080/list")
//        {
//            header("Accept", "application/json")
//        }
        val body = response.body<List<String>>()
        print("Body  -- " + body)
        callBack(body)

//        val contentType = response.headers["Content-Type"]
//        if (contentType != "application/json") {
//            // Handle error or response body differently
//            println("Unexpected Content-Type: $contentType")
//        } else {
//            // Read raw body as a string
//            val bodyAsString = response.bodyAsText()
//            // Manually parse the JSON response
//            val body = Json.decodeFromString<List<String>>(bodyAsString)
//            callBack(body)
//        }

    }

}