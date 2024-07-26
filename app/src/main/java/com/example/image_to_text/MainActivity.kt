package com.example.image_to_text

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.image_to_text.databinding.ActivityMainBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.camera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try{
                startActivityForResult(intent , 123)
            }catch(e:Exception){
                Toast.makeText(this,"Error while starting camera ${e.toString()}" , Toast.LENGTH_SHORT).show()
            }
        }

        binding.delete.setOnClickListener{
            binding.result.setText("")
        }

        binding.copy.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label" , binding.result.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,"Copied!!" , Toast.LENGTH_SHORT).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123 && resultCode== RESULT_OK){
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap
            Extract_Text(bitmap)
        }else{

        }
    }

    private fun Extract_Text(bitmap: Bitmap) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                binding.result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"Error while extracting ${e.toString()}" , Toast.LENGTH_SHORT).show()

            }


    }

}

