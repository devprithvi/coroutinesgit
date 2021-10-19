package com.ipie.dev.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity3 : AppCompatActivity() {
    private val TAG: String = "KOTLIN_FUN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        GlobalScope.launch(Dispatchers.Main) {
            executeTask()
        }
    }

    private suspend fun executeTask() {
        Log.d(TAG, "Before")

        // blockig call ...coroutine suspend {
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.d(TAG, "Inside")

        }
        Log.d(TAG, "After")

    }
}