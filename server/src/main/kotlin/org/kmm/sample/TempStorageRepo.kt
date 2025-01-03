package org.kmm.sample

import org.kmm.sample.model.ExpenseModel


/**
 * Created by Charles Raj I on 30/12/24
 * @project KmmSample
 * @author Charles Raj
 */


object SingleTon {
    val storageClient = TempStorageRepo()
}

class TempStorageRepo : TempStorageInterface {

    private val expenseList: MutableList<ExpenseModel> = mutableListOf()

    override fun saveExpense(expense: ExpenseModel) {
        expenseList.add(expense)
    }

    override fun fetchData() : List<ExpenseModel> {
        return expenseList

    }

    override fun findById(id: Int): ExpenseModel {
        return expenseList[id]
    }

}