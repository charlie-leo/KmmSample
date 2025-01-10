package org.kmm.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.kmm.sample.SharedExpenseViewModel
import org.kmm.sample.model.ExpenseModel

/**
 * Created by Charles Raj I on 05/01/25
 * @project KmmSample
 * @author Charles Raj
 */
class ExpenseViewModel(
    private val sharedExpenseViewModel: SharedExpenseViewModel
) : ViewModel()
{

//    val expenseList : StateFlow<List<ExpenseModel>> = sharedExpenseViewModel.expenseList
//    val totalExpenseAmount : StateFlow<String> = flow<String> {  }

    private val _expenseList = MutableStateFlow(listOf<ExpenseModel>())
    val expenseList: StateFlow<List<ExpenseModel>> get() = _expenseList

    init {
        viewModelScope.launch {
            sharedExpenseViewModel.fetchAllExpense{
                _expenseList.value = it.toMutableList()
            }
        }
    }

    fun addExpense(expenseModel: ExpenseModel){
        viewModelScope.launch {
            sharedExpenseViewModel.saveExpense(expenseModel){
                _expenseList.value = it.toMutableList()
            }
        }
    }

}