package org.kmm.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.kmm.sample.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {

    private val expenseViewModel : ExpenseViewModel = ExpenseViewModel(SharedExpenseViewModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(expenseViewModel)
        }
    }
}

