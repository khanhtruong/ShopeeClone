package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.data.model.BannerData
import com.khanhtruong.shopeeclone.databinding.ItemFeatureBannerBinding

class FeatureBannerComponentAdapter(
    private val adapter: FeatureBannerAdapter,
) : RecyclerView.Adapter<FeatureBannerComponentAdapter.FeatureBannerComponentViewHolder>() {

    inner class FeatureBannerComponentViewHolder(private val binding: ItemFeatureBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(adapter: FeatureBannerAdapter) {
            binding.viewPagerFeatureBanner.adapter = adapter
            binding.dotsIndicator.attachTo(binding.viewPagerFeatureBanner)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureBannerComponentViewHolder {
        return FeatureBannerComponentViewHolder(
            ItemFeatureBannerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FeatureBannerComponentViewHolder, position: Int) {
        holder.bindData(adapter)
    }

    override fun getItemCount() = 1
}