package org.kmm.sample.model

import kotlinx.serialization.Serializable

/**
 * Created by Charles Raj I on 30/12/24
 * @project KmmSample
 * @author Charles Raj
 */

@Serializable
data class ExpenseModel(
    val id : Int? = null,
    val amount : Double? = null,
    val type : String? = null,
    val descriptions : String? = null
)