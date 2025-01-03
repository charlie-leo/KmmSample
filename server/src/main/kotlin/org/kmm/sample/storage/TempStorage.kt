package org.kmm.sample.storage

import org.kmm.sample.model.ExpenseModel
import kotlin.math.exp

/**
 * Created by Charles Raj I on 02/01/25
 * @project KmmSample
 * @author Charles Raj
 */


object SingleTon {
    val storageClient = TempStorage()
}

class TempStorage : TempStorageInterface {

    private val expenseList: MutableList<ExpenseModel> = mutableListOf()


    override fun saveExpense(expenseModel: ExpenseModel) {
        expenseList.add(expenseModel)
    }

    override fun fetchData(): List<ExpenseModel> {
        return expenseList
    }

    override fun findById(id: Int): ExpenseModel {
        return expenseList[id]
    }


}