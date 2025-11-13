package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat // Import for hole punch safety
import com.example.myapplication.ui.theme.MyApplicationTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure content is laid out edge-to-edge, allowing safeDrawingPadding to work
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                ThirdActivityScreen()
            }
        }
    }
}

@Composable
fun ThirdActivityScreen() {
    // State to hold the captured image
    var capturedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = androidx.compose.ui.platform.LocalContext.current

    // 1. Launcher for the Camera Intent
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Use android.app.Activity.RESULT_OK to resolve the reference
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            // Get the Bitmap from the intent data (for thumbnail)
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            capturedImageBitmap = imageBitmap
        }
    }

    // 2. Launcher for Camera Permission Request
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, launch the camera
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(takePictureIntent)
        } else {
            // Permission denied (could show a message to the user)
        }
    }

    // Function to handle the button click logic
    val captureImage: () -> Unit = {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, launch camera
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(takePictureIntent)
        } else {
            // Permission not granted, request it
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            // USE safeDrawingPadding() to ensure the content is below the status bar/hole punch
            .safeDrawingPadding()
            .padding(horizontal = 16.dp), // Apply horizontal padding separately
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Capture Image" button
        Button(onClick = captureImage) {
            Text(text = "Capture Image")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display the captured image if available
        capturedImageBitmap?.let {
            Text(text = "Captured Image:")
            Spacer(modifier = Modifier.height(10.dp))
            // Display the Bitmap
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Captured Image",
                modifier = Modifier
                    .size(200.dp) // Set a reasonable size for the displayed image
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Button to go back to main activity (optional, but good practice)
        Button(onClick = {
            (context as? ComponentActivity)?.finish()
        }) {
            Text(text = "Back")
        }
    }
}