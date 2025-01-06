package org.kmm.sample

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.kmm.sample.NetworkClient.httpClient
import org.kmm.sample.model.ExpenseModel

class SharedExpenseViewModel {

    private val platform = getPlatform()
    private val apiService : ApiService = ApiService(httpClient)

    private val _expenseList = MutableStateFlow(listOf<ExpenseModel>())
    val expenseList: StateFlow<List<ExpenseModel>> = _expenseList


    suspend fun saveExpense(expenseModel: ExpenseModel){
        apiService.saveExpense(expenseModel){
            _expenseList.value = it.toMutableList()
        }
    }

    suspend fun fetchAllExpense(){
        apiService.fetchAllExpense {
            _expenseList.value = it.toMutableList()
        }
    }

    suspend fun findById(id:Int){
        apiService.findExpenseById(id){

        }
    }

}