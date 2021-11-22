package com.example.scareme3.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scareme3.R
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
        listTopic = ArrayList()

        binding.btnAvatar.setOnClickListener {

        }
    }

    private fun fillListTopic() {
        val disp = App.dm.api
            .getTopics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.code() == 200) {
                    
                }
            }, {
                println(it.message)
            })
    }
}