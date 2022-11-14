package com.khanhtruong.shopeeclone.data.model

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil

data class BannerData(
    val title: String,
    @DrawableRes val bannerImage: Int,
) {

    class BannerDataDiffCallback : DiffUtil.ItemCallback<BannerData>() {
        override fun areItemsTheSame(oldItem: BannerData, newItem: BannerData): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: BannerData, newItem: BannerData): Boolean {
            return oldItem == newItem
        }
    }
}
