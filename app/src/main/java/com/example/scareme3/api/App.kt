package com.example.scareme3.api

import android.app.Application

class App : Application () {
    companion object {
        lateinit var dm: DataManager
    }

    override fun onCreate() {
        super.onCreate()

        dm = DataManager(baseContext)
    }
}