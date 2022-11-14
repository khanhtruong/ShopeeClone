package com.khanhtruong.shopeeclone.data.model

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil

data class FirstDealData(
    @DrawableRes val image: Int,
    val description: String,
) {

    class FirstDealDiffCallBack : DiffUtil.ItemCallback<FirstDealData>() {
        override fun areItemsTheSame(oldItem: FirstDealData, newItem: FirstDealData): Boolean {
            return (oldItem.description == newItem.description) && (oldItem.image == newItem.image)
        }

        override fun areContentsTheSame(oldItem: FirstDealData, newItem: FirstDealData): Boolean {
            return oldItem == newItem
        }
    }
}