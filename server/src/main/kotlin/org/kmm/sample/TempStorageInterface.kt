package org.kmm.sample

import org.kmm.sample.model.ExpenseModel


/**
 * Created by Charles Raj I on 30/12/24
 * @project KmmSample
 * @author Charles Raj
 */
interface TempStorageInterface {


    fun saveExpense(expense : ExpenseModel)

    fun fetchData() : List<ExpenseModel>

    fun findById(id : Int) : ExpenseModel

}