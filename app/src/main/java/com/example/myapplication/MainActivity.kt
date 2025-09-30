package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Brennan Gerstner")
                    Text(text = "Student ID: 1354941")
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        // explicit intent to second activity
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)
                    }) {
                        Text(text = "Start Activity Explicitly")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        // implicit intent
                        val intent = Intent("com.example.myapplication.ACTION_VIEW_SECOND")
                        startActivity(intent)
                    }) {
                        Text(text = "Start Activity Implicitly")
                    }
                }
            }
        }
    }
}
