package com.example.pragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val myValue = MutableLiveData<Int>()
    init {
        myValue.value = 0
    }
    fun increaseCount(){
        myValue.value = (myValue.value ?: 0) + 1
    }
}