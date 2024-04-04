package id.alterdev.alterattendance.features.scan

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class ScanCodeAnalyzer(
    private val callback: (String) -> Unit
) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(image: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC
            )
            .build()

        val scanner = BarcodeScanning.getClient(options)
        val mediaImage = image.image
        mediaImage?.let {
            val imageInput = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

            scanner.process(imageInput)
                .addOnSuccessListener { barcodes ->
                    val result = barcodes?.takeIf { it.isNotEmpty() }
                        ?.mapNotNull { it.rawValue }
                        ?.joinToString(",")

                    if (result != null) {
                        callback(result)
                    }
                }
                .addOnCompleteListener {
                    image.close()
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }
    }
}