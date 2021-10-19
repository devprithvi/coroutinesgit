package com.ipie.dev.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity2 : AppCompatActivity() {
    private val TAG: String = "KOTLIN_FUN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }
    }
/*
    private suspend fun execute() {
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "Parent - $coroutineContext")
            Log.d(TAG, "Parent Started")


            //childJob inherit the context of parent job
            //we can explicitly define child job thread...
            val childJob = launch(Dispatchers.IO) {
                Log.d(TAG, "child - $coroutineContext")
                Log.d(TAG, "Child Started")
                delay(5000)
                Log.d(TAG, "Child Ended")


            }
            delay(3000)
            Log.d(TAG, "Child Job Cancelled")
            childJob.cancel()
            Log.d(TAG, "Parent Ended")
        }
        delay(1000)
        //parentJob.cancel() //when user switched to another thing::: >????
        parentJob.join()
        Log.d(TAG, "Parent Completed")

    }

 */

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {

            for (i in 1..1000) {
                if (isActive){
                    executeLongRunningTask()
                    Log.d(TAG, i.toString())
                }

            }
        }
        delay(100)
        Log.d(TAG, "Cancelling Job")
        parentJob.cancel()
        Log.d(TAG, "Parent Completed")

    }

    private fun executeLongRunningTask() {

    }
}