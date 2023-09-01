package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private val a = mutableStateOf(0)
    private val b = mutableStateOf(0)
    private val c = mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Input("a", a)
                        Input("b", b)
                        Row {
                            Operation(name = "+")
                            Operation(name = "-")
                            Operation(name = "*")
                            Operation(name = "/")
                        }
                        Text(text = c.value.toString(), Modifier.padding(30.dp))
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Input(name: String, state: MutableState<Int>, modifier: Modifier = Modifier) {
        var value by remember { state }
        TextField(
            value.toString(),
            { value = it.toInt() },
            label = { Text(text = name) },
            modifier = modifier.padding(10.dp)
        )
    }

    @Composable
    fun Operation(name: String, modifier: Modifier = Modifier) {
        Button(onClick = {
            when (name) {
                "+" -> add()
                "-" -> subtract()
                "*" -> multiply()
                "/" -> divide()
            }
        }, modifier = modifier.padding(10.dp)) {
            Text(text = name)
        }
    }

    private fun add(){
        c.value = a.value + b.value
    }

    private fun subtract(){
        c.value = a.value - b.value
    }

    private fun multiply(){
        c.value = a.value * b.value
    }

    private fun divide(){
        if(b.value != 0) c.value = a.value / b.value
    }
}
