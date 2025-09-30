package com.example.myapplication

import android.content.Intent
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

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Challenges in Mobile Software Engineering:")
                    Spacer(modifier = Modifier.height(8.dp))
                    // List at least 5 challenges
                    Text(text = "1. Device fragmentation (different screen sizes, OS versions)")
                    Text(text = "2. Battery constraints / energy efficiency")
                    Text(text = "3. Network connectivity and offline support")
                    Text(text = "4. Performance / memory constraints")
                    Text(text = "5. Security and privacy (permissions, data encryption)")
                    // (you can add more, e.g. testing on many devices, accelerators, etc.)
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {
                        // go back to main activity
                        val intent = Intent(this@SecondActivity, MainActivity::class.java)
                        // Optionally clear the stack or flags if needed
                        startActivity(intent)
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Main Activity")
                    }
                }
            }
        }
    }
}
