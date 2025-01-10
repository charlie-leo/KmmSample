package org.kmm.sample

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.kmm.sample.NetworkClient.httpClient
import org.kmm.sample.model.ExpenseModel

class SharedExpenseViewModel {

    private val platform = getPlatform()
    private val apiService : ApiService = ApiService(httpClient)

//    private val _expenseList = MutableStateFlow(listOf<ExpenseModel>())
//
//    @NativeCoroutinesState
//    val expenseList: StateFlow<List<ExpenseModel>> get() = _expenseList


    suspend fun saveExpense(expenseModel: ExpenseModel, callBack : (List<ExpenseModel>) -> Unit ){
        apiService.saveExpense(expenseModel, callBack)
    }

    suspend fun fetchAllExpense(callBack : (List<ExpenseModel>) -> Unit){
        apiService.fetchAllExpense(callBack)
//        {
//            _expenseList.value = it.toMutableList()
//        }
    }

    suspend fun findById(id:Int){
        apiService.findExpenseById(id){

        }
    }
}

fun <T> StateFlow<T>.asFlow(): Flow<T> = this