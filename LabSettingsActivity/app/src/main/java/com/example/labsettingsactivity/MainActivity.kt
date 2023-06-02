package com.example.labsettingsactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener{
            startActivity(Intent(this,SettingsActivity::class.java))
        }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onStart() {
        super.onStart()

        val tv=findViewById<TextView>(R.id.textView)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val name = pref.getString("name", "")
        val sizeString = pref.getString("size", "small")
        val sizeFloat = when (sizeString) {
            "small" -> 10f
            "medium" -> 14f
            "big" -> 20f
            else -> 14f
        }
        val italic = pref.getBoolean("italic", false)

        tv.text=name
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeFloat)
        if (italic) {
            tv.setTypeface(null, Typeface.ITALIC) // 이태릭체로 설정
        } else {
            tv.setTypeface(null, Typeface.NORMAL) // 노멀로 설정
        }

    }
}