package com.example.intenttest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val number = it.data?.getStringExtra("resultdata") ?: ""
            findViewById<EditText>(R.id.editText).setText(number)

        }

        findViewById<Button>(R.id.button)?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val number = findViewById<EditText>(R.id.editText).text
            intent.putExtra("userdata", "${number}")
            //startActivity(intent)
            activityResult.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        println("################## MainAcitivty - onStart ################")
    }

    override fun onResume() {
        super.onResume()
        println("################## MainAcitivty - onResume ################")
    }

    override fun onPause() {
        super.onPause()
        println("################## MainAcitivty - onPause ################")
    }

    override fun onStop() {
        super.onStop()
        println("################## MainAcitivty - onStop ################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("################## MainAcitivty - onDestroy ################")
    }
}