package com.example.recyclerviewui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val viewModel by viewModels<MyViewModel>()

        viewModel.addItem(Item("John","Baker"))
        viewModel.addItem(Item("은수","장"))
        viewModel.addItem(Item("안드로이드","13"))

        val adapter = CustomAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        findViewById<FloatingActionButton>(R.id.floatingActionButton)?.setOnClickListener{
            viewModel.addItem(Item("Test","Test"))
            adapter.notifyDataSetChanged()
        }

        viewModel.clickItem.observe(this){
            val item = viewModel.items[it]
            ItemDialog(item).show(supportFragmentManager,"")
        }
    }
}