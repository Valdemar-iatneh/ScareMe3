package com.example.scareme3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.scareme3.api.App
import com.example.scareme3.common.BaseActivity
import com.example.scareme3.databinding.ActivitySplashBinding
import com.example.scareme3.model.User
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            if (App.dm.getEmail().isNullOrEmpty()) {
                startActivityWithFinish(WelcomeActivity::class.java)
            }
            else {
                val disp = App.dm.api
                    .autorization(User(App.dm.getEmail()!!, App.dm.getPassword()!!))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                            App.dm.setToken(it.body()!!.accessToken)
                        }, {
                            println(it.message)
                        }
                    )
            }
        },2000)
    }
}