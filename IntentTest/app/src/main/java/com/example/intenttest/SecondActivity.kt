package com.example.intenttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val resultIntent = Intent()
                val resultNumber = findViewById<TextView>(R.id.textView).text
                resultIntent.putExtra("resultdata", resultNumber)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        })

        val number = intent?.getStringExtra("userdata")?.toInt() ?: 0

        class MyViewModelFactory(private val initialCount: Int) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(Int::class.java).newInstance(initialCount)
            }
        }

        val viewModel = ViewModelProvider(this, MyViewModelFactory(number))[MyViewModel::class.java]

        viewModel.countLiveData.observe(this, {
            findViewById<TextView>(R.id.textView).text = it.toString()
        })

        findViewById<Button>(R.id.buttonInc)?.setOnClickListener {
            viewModel.increaseCount()
        }

        findViewById<Button>(R.id.buttonDec)?.setOnClickListener {
            viewModel.decreaseCount()
        }
    }

    override fun onStart() {
        super.onStart()
        println("################## SecondActivity - onStart ################")
    }

    override fun onResume() {
        super.onResume()
        println("################## SecondActivity - onResume ################")
    }

    override fun onPause() {
        super.onPause()
        println("################## SecondActivity - onPause ################")
    }

    override fun onStop() {
        super.onStop()
        println("################## SecondActivity - onStop ################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("################## SecondActivity - onDestroy ################")
    }
}