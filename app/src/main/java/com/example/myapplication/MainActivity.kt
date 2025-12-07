package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = 
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
                startSecondActivity()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

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
                        when {
                            ContextCompat.checkSelfPermission(
                                this@MainActivity,
                                "com.example.myapplication.MSE412"
                            ) == PackageManager.PERMISSION_GRANTED -> {
                                startSecondActivity()
                            }
                            else -> {
                                requestPermissionLauncher.launch("com.example.myapplication.MSE412")
                            }
                        }
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
                    Spacer(modifier = Modifier.height(10.dp))
                    // **NEW BUTTON ADDED**
                    Button(onClick = {
                        // explicit intent to third activity
                        val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                        startActivity(intent)
                    }) {
                        Text(text = "View Image Activity")
                    }
                }
            }
        }
    }

    private fun startSecondActivity() {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}