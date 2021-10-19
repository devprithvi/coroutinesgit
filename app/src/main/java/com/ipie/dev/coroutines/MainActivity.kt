package com.ipie.dev.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val TAG: String = "KOTLIN_FUN"
    lateinit var value_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        value_txt = findViewById(R.id.counterText)
        Log.d(TAG, "${Thread.currentThread().name}")


    }

    fun updateCounter(view: View) {
        Log.d(TAG, "${Thread.currentThread().name}")

        value_txt.text = "${value_txt.text.toString().toInt() + 1}"
    }

    private fun executeLongRunningTask() {
        for (i in 1..1000000000000L) {

        }
    }

    fun doAction(view: android.view.View) {
        // executeLongRunningTask() // if we run directly the ui or we cansay main thread will be freeze...
        //create a new a thread // one thread will take around 1MB Space..limited creation(Problem with thread )
//        thread(start = true) {
//            executeLongRunningTask()
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d(TAG, "1 - ${Thread.currentThread().name}")
//            executeLongRunningTask()
//        }

//        GlobalScope(Dispatchers.Main).launch {
//            Log.d(TAG, "2 - ${Thread.currentThread().name}")
//
//        }
//        MainScope().launch(Dispatchers.Default) {
//            Log.d(TAG, "3 - ${Thread.currentThread().name}")
//
//        }

        val job = CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }
        //job.cancel()   ->> //to cancel the coroutine..
        //job.join()  //kepp coroutine in suspended state...


        CoroutineScope(Dispatchers.Main).launch {

            task1()
        }
        CoroutineScope(Dispatchers.Main).launch {

            task2()
        }

    }

    //    private suspend fun printFollowers() {
//        var fbFollowers = 0
//        var instaFollowers = 0
//
//        //launch a coroutine  ;;Stroe in job
//        val job = CoroutineScope(Dispatchers.IO).launch {
//            fbFollowers = getFbFollowers()
//        }
//        val job2 = CoroutineScope(Dispatchers.IO).launch {
//            instaFollowers = getInstaFollowers()
//        }
//
//        job.join() //after complete join the ...next part will done
//        //job: it is corountine handle ...to start or join or cancel the job ...'
//        job2.join()
//        Log.d(TAG, "FB - $fbFollowers  Insta - $instaFollowers")
//    }
//    private suspend fun printFollowers() {
//
//        val fb = CoroutineScope(Dispatchers.IO).async {
//            getFbFollowers()
//        }
//        val insta = CoroutineScope(Dispatchers.IO).async {
//            getInstaFollowers()
//        }
//        Log.d(TAG, "Fb - ${fb.await()}  Insta - ${insta.await()}")  //here we wait for the function..to get the data ...fb and insta...
//    }


    private suspend fun printFollowers() {
        CoroutineScope(Dispatchers.IO).launch {
            var fb = async { getFbFollowers() }
            var insta = async { getInstaFollowers() }
            Log.d(TAG, "Fb - ${fb.await()} Insta- ${insta.await()}")
        }

    }


    suspend fun task1() {
        Log.d(TAG, "Starting task 1")
        yield()
        //delay : also create suspension function...
        Log.d(TAG, "End task 1")


    }

    suspend fun task2() {
        Log.d(TAG, "Starting task 2")
        yield()
        Log.d(TAG, "End task 2")
    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 541
    }

    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 110
    }
}