package com.example.carousellnews.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carousellnews.databinding.ItemCarousellNewsBinding
import com.example.carousellnews.entities.News
import com.example.carousellnews.ext.getDifferenceWithCurrentDateTime
import com.squareup.picasso.Picasso


class NewsAdapter(
    private var news: List<News>
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCarousellNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news[position]
        holder.descriptionTv
        holder.titleTv.text = item.title
        holder.descriptionTv.text = item.description
        holder.createdTimeTv.text = getDifferenceWithCurrentDateTime(item.time_created)

        Picasso.with(holder.itemView.context).load(item.banner_url).fit().centerCrop()
            // .placeholder(R.drawable.user_placeholder)
            // .error(R.drawable.user_placeholder_error)
            .into(holder.newsIv)

    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(binding: ItemCarousellNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleTv: TextView = binding.titleTv
        val descriptionTv: TextView = binding.descriptionTv
        val createdTimeTv: TextView = binding.createdTimeTv
        val newsIv: ImageView = binding.newsIv

    }

    fun sortByPopular() {
        this.news.sortedBy { it.rank }.also { this.news = it }
        notifyData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun notifyData() = notifyDataSetChanged()

    fun sortByRecent() {
        this.news.sortedByDescending { it.time_created }.also { this.news = it }
        notifyData()
    }

}