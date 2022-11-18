package com.khanhtruong.shopeeclone.data.model

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil

data class ProductData(
    val id: Int,
    @DrawableRes val snapshot: Int,
    val price: Double,
    val sold: Double,
    val title: String,
) {
    class ProductDiffCallBack : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem == newItem
        }
    }
}