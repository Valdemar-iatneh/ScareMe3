package com.example.scareme3.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scareme3.adapter.TopicRecViewAdapter
import com.example.scareme3.api.App
import com.example.scareme3.databinding.ActivityProfileBinding
import com.example.scareme3.model.Topic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var listTopic: ArrayList<Topic>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var topicRecView: RecyclerView = binding.recViewTopic
        topicRecView.layoutManager = GridLayoutManager(this, 4)
        topicRecView.itemDecorationCount
        fillListTopic()

        binding.btnAvatar.setOnClickListener {

        }
    }

    private fun fillListTopic() {
        val disp = App.dm.api
            .getTopics("Bearer ${App.dm.getToken()}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("topic", it.code().toString())
                if (it.code() == 200) {
                    Log.i("topic", "ok")
                    binding.recViewTopic.adapter = TopicRecViewAdapter(this, it.body()!!)
                }
            }, {
                println(it.message)
            })
    }
}