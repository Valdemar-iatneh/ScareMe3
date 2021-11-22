package com.example.scareme3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scareme3.R
import com.example.scareme3.databinding.RecviewItemTopicBinding
import com.example.scareme3.model.Topic

class TopicRecViewAdapter(var context: Context, var listTopic: List<Topic>) :
    RecyclerView.Adapter<TopicRecViewAdapter.RVView> (){

    lateinit var binding: RecviewItemTopicBinding
    class RVView(var itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVView {
        binding = RecviewItemTopicBinding.bind(
            LayoutInflater.from(context).inflate(
                R.layout.recview_item_topic, parent, false
            )
        )
        return RVView(binding.root)
    }

    override fun onBindViewHolder(holder: RVView, position: Int) {
        binding.textTopic.text = listTopic[position].title
    }

    override fun getItemCount() = listTopic.size
}