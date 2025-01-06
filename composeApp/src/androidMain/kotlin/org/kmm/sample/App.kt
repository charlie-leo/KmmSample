package org.kmm.sample

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.kmm.sample.model.ExpenseModel
import org.kmm.sample.viewmodel.ExpenseViewModel

@Composable
fun App(expenseViewModel: ExpenseViewModel) {

    val viewModel = expenseViewModel
    val expenseList by expenseViewModel.expenseList.collectAsState()

    val showModelSheet = remember { mutableStateOf(true) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmValueChange = { it ->

//            if (it == ModalBottomSheetValue.Hidden){
//                showModelSheet.value = false
//            } else {
//                showModelSheet.value = true
//            }
//            Log.d("TAG", "App confirm: ${showModelSheet.value}")

            true
        }
    )
    val scope = rememberCoroutineScope()


    val amount = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    LaunchedEffect(showModelSheet) {
        scope.launch {
            if (showModelSheet.value) {
                sheetState.show()
            } else {
                sheetState.hide()
            }
            Log.d("TAG", "App scope: ${showModelSheet.value}")
        }
    }

    MaterialTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "$32.08",
                        modifier = Modifier,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(expenseList.size) { it ->
                        val expense = expenseList[it]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Text(
                                    expense.descriptions ?: "",
                                    modifier = Modifier,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                                Text(
                                    expense.type ?: "",
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }

                            Text(
                                expense.amount.toString() ?: "",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                            )


                        }
                    }
                }
            }

            FloatingActionButton(
                onClick = {
//                    showModelSheet.value = !showModelSheet.value
//                    if (sheetState.isVisible){
//                        showModelSheet.value = false
//                    } else {
//                        showModelSheet.value = true
//                    }
                    scope.launch {
                        if (sheetState.isVisible) {
                            sheetState.hide()
                        } else {
                            sheetState.show()
                        }
                        Log.d("TAG", "App scope: ${showModelSheet.value}")
                    }
//                    Log.d("TAG", "App: ${showModelSheet.value}")
                },
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomEnd)
                    .padding(15.dp),
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Filled.Add,
                    "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }

        ModalBottomSheetLayout(
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(15.dp)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Add Expense",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    TextField(
                        value = amount.value,
                        onValueChange = {
                            amount.value = it
                        },
                        placeholder = {
                            Text("Amount")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            placeholderColor = Color.Gray,
                            textColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = type.value,
                        onValueChange = {
                            type.value = it
                        },
                        placeholder = {
                            Text("Type")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            placeholderColor = Color.Gray,
                            textColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = description.value,
                        onValueChange = {
                            description.value = it
                        },
                        placeholder = {
                            Text("Description")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            placeholderColor = Color.Gray,
                            textColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
//                            if (sheetState.isVisible){
//                                showModelSheet.value = false
//                            } else {
//                                showModelSheet.value = true
//                            }
                            scope.launch {
                                if (sheetState.isVisible) {
                                    sheetState.hide()
                                } else {
                                    sheetState.show()
                                }
                                Log.d("TAG", "App scope: ${showModelSheet.value}")
                            }
                            viewModel.addExpense(
                                ExpenseModel(
                                    amount = amount.value.toDouble(),
                                    type = type.value,
                                    descriptions = description.value
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            "Add",
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            sheetState = sheetState,
            sheetBackgroundColor = Color.White,
            sheetContentColor = Color.Black,
            sheetShape = RoundedCornerShape(16.dp),
            sheetGesturesEnabled = true,
            sheetElevation = 10.dp,
        ) {

        }
    }
}

@Composable
fun BottomSheet(){

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    App(ExpenseViewModel(SharedExpenseViewModel()))
}












