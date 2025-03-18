package com.gabitovairat.yplazycolomn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabitovairat.yplazycolomn.ui.theme.YPLazyColomnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YPLazyColomnTheme {
                SimpleLazeColomn(
                    (1..1000000).toList().map { it.toString() }.toMutableList()
                )
            }
        }
    }
}

//Дополнительное задание: сделаейте в списке расделы, т.е. прикрепленные заголовки
//пример https://metanit.com/kotlin/jetpack/2.11.php

@Composable
fun SimpleLazeColomn(customItems: MutableList<String>) {
    var photos by remember { mutableStateOf(customItems) }

    Box {

        LazyColumn (modifier = Modifier.fillMaxSize()) {
            itemsIndexed(
                items = photos,
                key = { index, item -> item.toInt() }
            ) { index, item ->
                Row (modifier = Modifier
                    .padding(12.dp)
                    .border(2.dp, color = Color.Magenta)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "Back"
                    )
                    Text(text = item, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            }
        }

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {

            Button(onClick = {
                photos = photos.toMutableList().also {
                    it.add(0, "666")
                }
            }){
                Text(text = "Add")
            }

            Button(
                onClick = {
                    photos = photos.toMutableList().apply { this.drop(1) }
                })
            {
                Text(text = "Remove")
            }

            Button(
                onClick = {
                    photos = photos.toMutableList().apply { this.shuffle() }
                })
            {
                Text(text = "Shuffle")
            }
        }
    }



}


@Composable
fun SimpleColomnScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
    ) {
      repeat(1000) { value ->
          Row (modifier = Modifier.padding(12.dp)) {
              Text(text = value.toString(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
          }
      }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YPLazyColomnTheme {
        SimpleLazeColomn(
            (1..100).toList().map { it.toString() }.toMutableList()
        )
    }
}