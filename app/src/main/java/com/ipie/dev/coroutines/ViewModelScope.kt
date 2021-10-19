package com.ipie.dev.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelScope : AppCompatActivity() {

    lateinit var viwModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_scope)

        viwModel = ViewModelProvider(this).get(MainViewModel::class.java)

        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@ViewModelScope, AnotherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}