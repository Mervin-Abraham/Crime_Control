package com.example.crime_control

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Utility class to read images from internal storage
class FileReader( private var context: Context ) {

    private val realTimeOpts = FaceDetectorOptions.Builder()
        .setPerformanceMode( FaceDetectorOptions.PERFORMANCE_MODE_FAST )
        .build()
    private val detector = FaceDetection.getClient( realTimeOpts )
    private val faceNetModel = FaceNetModel( context )
    private val coroutineScope = CoroutineScope( Dispatchers.Main )
    private var numImagesWithNoFaces = 0
    private var imageCounter = 0
    private var numImages = 0
    private var data = ArrayList<Pair<String,Bitmap>>()
    private lateinit var callback: ProcessCallback

    // imageData will be provided to the MainActivity via ProcessCallback ( see the run() method below ) and finally,
    // used by the FrameAnalyser class.
    private val imageData = ArrayList<Pair<String,FloatArray>>()



    // Given the Bitmaps, extract face embeddings from then and deliver the processed embedding to ProcessCallback.
    fun run( data : ArrayList<Pair<String,Bitmap>> , callback: ProcessCallback) {
        numImages = data.size
        this.data = data
        this.callback = callback
        scanImage( data[ imageCounter ].first , data[ imageCounter ].second )
    }


    interface ProcessCallback {
        fun onProcessCompleted( data : ArrayList<Pair<String,FloatArray>> , numImagesWithNoFaces : Int )
    }


    // Crop faces and produce embeddings ( using FaceNet ) from given image.
    // Store the embedding in imageData
    private fun scanImage( name : String , image : Bitmap ) {
        val inputImage = InputImage.fromByteArray(
            BitmapUtils.bitmapToNV21ByteArray(image),
            image.width,
            image.height,
            0,
            InputImage.IMAGE_FORMAT_NV21
        )
        detector.process( inputImage )
            .addOnSuccessListener { faces ->
                if ( faces.size != 0 ) {
                    coroutineScope.launch{
                        val embedding = getEmbedding( image , faces[ 0 ].boundingBox )
                        imageData.add( Pair( name , embedding ) )
                        // Embedding stored, now proceed to the next image.
                        if ( imageCounter + 1 != numImages ) {
                            imageCounter += 1
                            scanImage( data[ imageCounter].first , data[ imageCounter ].second )
                        }
                        else {
                            // Processing done, reset the file reader.
                            callback.onProcessCompleted( imageData , numImagesWithNoFaces )
                            reset()
                        }
                    }
                }
                else {
                    // The image contains no faces, proceed to the next one.
                    numImagesWithNoFaces += 1
                    if ( imageCounter + 1 != numImages ) {
                        imageCounter += 1
                        scanImage( data[ imageCounter].first , data[ imageCounter ].second )
                    }
                    else {
                        callback.onProcessCompleted( imageData , numImagesWithNoFaces )
                        reset()
                    }
                }

            }
            .addOnFailureListener { e ->
                Log.e( "App" , e.message!! )
            }
    }


    // Suspend function for running the FaceNet model
    private suspend fun getEmbedding( croppedFaceBitmap: Bitmap , bbox : Rect ) =
        withContext( Dispatchers.Default ) {
            return@withContext faceNetModel.getFaceEmbedding(croppedFaceBitmap , bbox)
        }


    private fun reset() {
        imageCounter = 0
        numImages = 0
        numImagesWithNoFaces = 0
        data.clear()
    }

}