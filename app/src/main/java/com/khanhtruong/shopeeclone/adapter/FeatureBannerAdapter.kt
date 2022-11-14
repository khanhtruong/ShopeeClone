package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.khanhtruong.shopeeclone.data.model.BannerData
import com.khanhtruong.shopeeclone.databinding.ItemImageViewBinding

class FeatureBannerAdapter(
    private val onClick: (BannerData) -> Unit
) : ListAdapter<BannerData, FeatureBannerAdapter.FeatureBannerViewHolder>(BannerData.BannerDataDiffCallback()) {

    inner class FeatureBannerViewHolder(private val binding: ItemImageViewBinding) :
        ViewHolder(binding.root) {
        fun bindData(data: BannerData, onClick: (BannerData) -> Unit) {
            binding.imageViewFeatureBanner.setImageResource(data.bannerImage)
            binding.root.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureBannerViewHolder {
        return FeatureBannerViewHolder(
            ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeatureBannerViewHolder, position: Int) {
        holder.bindData(currentList[position], onClick)
    }
}