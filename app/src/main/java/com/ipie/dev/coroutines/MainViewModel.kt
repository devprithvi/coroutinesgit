package com.ipie.dev.coroutines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val TAG: String = "KOTLIN_FUN"

    init {
        viewModelScope.launch {

            while (true){
                delay(500)
                Log.d(TAG,"Hello form Ipie")
            }
        }

    }

    //on Destroy of viewModel this func will be called..
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"View Model Destroyed")

    }
}