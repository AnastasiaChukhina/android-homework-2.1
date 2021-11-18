package com.itis.android_homework.adapter.cardView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.android_homework.databinding.ViewPager2ImageBinding

class ViewPagerAdapter(
    private val img: List<String>,
    private val glide: RequestManager
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerHolder = ViewPagerHolder(
        ViewPager2ImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ViewPagerHolder,
        position: Int
    ) = holder.bind(img[position])


    override fun getItemCount(): Int = img.size

    inner class ViewPagerHolder(
        private val binding: ViewPager2ImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val options = RequestOptions()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        fun bind(url: String) {
            binding.apply {
                glide.load(url)
                    .apply(options)
                    .into(ivImage)
            }
        }
    }
}
