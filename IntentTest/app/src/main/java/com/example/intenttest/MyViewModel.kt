package com.example.intenttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(initialCount: Int) : ViewModel() {

    val countLiveData = MutableLiveData<Int>()

    init {
        countLiveData.value = initialCount
    }

    fun increaseCount(){
        countLiveData.value = (countLiveData.value ?: 0) + 1
    }

    fun decreaseCount(){
        countLiveData.value = (countLiveData.value ?: 0) - 1
    }
}
