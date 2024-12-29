package org.kmm.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import kmmsample.composeapp.generated.resources.Res
import kmmsample.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch

@Composable
fun App() {
    val greeting = remember { Greeting().greet() }
    var numList = remember { listOf<String>() }
    val scope = rememberCoroutineScope()

    var list = produceState(initialValue = listOf<String>()){
        scope.launch {
            Greeting().getList {
                value = it.toMutableList()
            }
        }
    }

//    LaunchedEffect(Unit) {
//        scope.launch {
//            Greeting().getList {
//                numList = it.toMutableList()
//            }
//        }
//    }

//    Greeting().getList()

    var showContent by remember { mutableStateOf(false) }

    MaterialTheme {

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(30.dp)
        ) {
            LazyColumn {
                items(list.value.size) { it ->
                    val value = list.value[it]
                    Text(value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )

                }
            }

        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    App()
}