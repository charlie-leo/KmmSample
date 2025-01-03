package org.kmm.sample

import org.kmm.sample.NetworkClient.httpClient

class Greeting {
    private val platform = getPlatform()

    private val apiService : ApiService = ApiService(httpClient)

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun getList(callback : (value : List<String>) -> Unit) {
        apiService.getUserList {
            callback(it)
        }
    }

}