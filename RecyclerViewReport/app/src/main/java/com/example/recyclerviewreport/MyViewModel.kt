package com.example.recyclerviewreport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(val Name: String, val Address: String)

enum class ItemEvent { ADD, UPDATE, DELETE }

class MyViewModel : ViewModel() {
    val itemsListData = MutableLiveData<ArrayList<Item>>()
    val items = ArrayList<Item>()
    var itemsEvent = ItemEvent.ADD
    var itemsEventPos = -1

    val itemClickEvent = MutableLiveData<Int>()
    var itemLongClick = -1

    init {
        items.add(Item("Yuh-jung", "Youn"))
        items.add(Item("Steven", "Yeun"))
        items.add(Item("Alan", "Kim"))
        itemsListData.value = items
    }

    fun getItem(pos: Int) =  items[pos]
    val itemsSize
        get() = items.size

    fun addItem(item: Item) {
        itemsEvent = ItemEvent.ADD
        itemsEventPos = itemsSize
        items.add(item)
        itemsListData.value = items // let the observer know the livedata changed
    }

    fun updateItem(pos: Int, item: Item) {
        itemsEvent = ItemEvent.UPDATE
        itemsEventPos = pos
        items[pos] = item
        itemsListData.value = items // 옵저버에게 라이브데이터가 변경된 것을 알리기 위해
    }

    fun deleteItem(pos: Int) {
        itemsEvent = ItemEvent.DELETE
        itemsEventPos = pos
        items.removeAt(pos)
        itemsListData.value = items
    }
}