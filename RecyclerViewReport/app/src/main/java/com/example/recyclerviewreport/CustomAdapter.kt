package com.example.recyclerviewreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val viewModel: MyViewModel) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    }

    // ViewHolder 생성, ViewHolder 는 View 를 담는 상자
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.item_layout, viewGroup, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            viewModel.itemClickEvent.value = viewHolder.adapterPosition
        }
        view.setOnLongClickListener {
            viewModel.itemLongClick = viewHolder.adapterPosition
            false // for context menu
        }
        return viewHolder
    }

    // ViewHolder 에 데이터 쓰기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val text1 = view.findViewById<TextView>(R.id.textView)
        val text2 = view.findViewById<TextView>(R.id.textView2)

        text1.text = viewModel.items[position].Name
        text2.text = viewModel.items[position].Address
    }

    override fun getItemCount() = viewModel.itemsSize
}