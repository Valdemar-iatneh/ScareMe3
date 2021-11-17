package com.example.scareme3.common

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun startActivity(classActivity: Class<*>) {
        startActivity(Intent(applicationContext, classActivity))
    }

    fun startActivityWithFinish(classActivity: Class<*>, affinity: Boolean) {
        startActivity(Intent(applicationContext, classActivity))

        finish()
    }
}