package id.alterdev.alterattendance.features.scan

import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import id.alterdev.alterattendance.features.home.HomeViewModel
import id.alterdev.alterattendance.ui.navigation.Screen
import java.util.concurrent.Executors

@Composable
fun ScannerScreen(navController: NavController) {
    var codeScanned by remember { mutableStateOf(false) }
    val localContext = LocalContext.current

    Scanner(onScan = { scannedCode ->
        if (!codeScanned) {
            codeScanned = true

            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Scanner.route)
            }

            Toast.makeText(localContext, scannedCode, Toast.LENGTH_SHORT).show()

        }
    })
}

@Composable
fun Scanner(
    onScan: (String) -> Unit
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(localContext)
    }

    AndroidView(
        factory = { context ->
            val cameraExecutor = Executors.newSingleThreadExecutor()
            val scanner = PreviewView(context).also {
                it.scaleType = PreviewView.ScaleType.FILL_CENTER
            }
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(scanner.surfaceProvider)
                    }

                val imageCapture = ImageCapture.Builder().build()

                val imageAnalyzer = ImageAnalysis.Builder()
                    .build()
                    .also { imageAnalysis ->
                        imageAnalysis.setAnalyzer(cameraExecutor, ScanCodeAnalyzer(
                            callback = {
                                if (it != "") {
                                    onScan(it)
                                }
                            }
                        ))
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture,
                        imageAnalyzer
                    )
                } catch (e: Exception) {
                    Log.e("DEBUG", "ScannerScreen: Use case binding failed", e)
                }
            }, ContextCompat.getMainExecutor(context))
            scanner
        },
        modifier = Modifier
            .fillMaxSize()
    )
}