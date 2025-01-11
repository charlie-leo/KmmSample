package org.kmm.sample

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.serialization.json.Json
import org.kmm.sample.model.ExpenseModel

/**
 * Created by Charles Raj I on 28/12/24
 * @project KmmSample
 * @author Charles Raj
 */
class ApiService(private val client : HttpClient) {

    private val BASE_URL : String = "http://10.0.2.2:8080"

    suspend fun saveExpense(expense : ExpenseModel, callBack: (value: List<ExpenseModel>) -> Unit){
        println("response : $expense")
        try {
            val response = client.post("${getPlatform().ipAddress}/save"){

                contentType(ContentType.Application.Json)
                setBody(expense)

            }
            val body = response.body<List<ExpenseModel>>()
            callBack(body)

        } catch (e : Exception){
            println(e)
        }
    }

    suspend fun fetchAllExpense(callBack: (value: List<ExpenseModel>) -> Unit){
        try {
            val response = client.get("$BASE_URL/getAllExpense"){

            }
            val body = response.body<List<ExpenseModel>>()
            callBack(body)
        } catch (e : Exception) {
            println(e)
        }

    }

    suspend fun findExpenseById(id : Int, callBack: (value: ExpenseModel) -> Unit){
        try {
            val response = client.get("$BASE_URL/findById/$id"){

            }
            val body = response.body<ExpenseModel>()
            callBack(body)
        } catch (e : Exception){
            println(e)
        }
    }

}