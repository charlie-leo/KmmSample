package org.kmm.sample.storage

import org.kmm.sample.model.ExpenseModel

/**
 * Created by Charles Raj I on 02/01/25
 * @project KmmSample
 * @author Charles Raj
 */
interface TempStorageInterface {

    fun saveExpense(expenseModel: ExpenseModel)
    fun fetchData() : List<ExpenseModel>
    fun findById(id: Int) : ExpenseModel

}