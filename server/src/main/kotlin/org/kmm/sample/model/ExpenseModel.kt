package org.kmm.sample.model

import kotlinx.serialization.Serializable

/**
 * Created by Charles Raj I on 02/01/25
 * @project KmmSample
 * @author Charles Raj
 */


@Serializable
class ExpenseModel (
     val id : Int? = null,
    val amount: Double? = null,
    val type: String? = null,
    val description : String? = null
)