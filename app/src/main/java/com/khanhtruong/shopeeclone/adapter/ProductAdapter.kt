package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.model.ProductData
import com.khanhtruong.shopeeclone.databinding.ItemProductBinding

class ProductAdapter(
    private val onClick: (ProductData) -> Unit
) : ListAdapter<ProductData, ProductAdapter.ProductViewHolder>(ProductData.ProductDiffCallBack()) {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ProductData, onClick: (ProductData) -> Unit) {
            val context = binding.root.context

            binding.textViewProductTitle.text = data.title
            binding.textViewProductPrice.text =
                context.getString(R.string.fmt_item_price, "đ", data.price.toString())
            binding.textViewProductSold.text =
                context.getString(R.string.fmt_item_sold, data.sold.toString())
            binding.imageViewProductSnapshot.setImageResource(data.snapshot)

            binding.root.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(currentList[position], onClick)
    }
}