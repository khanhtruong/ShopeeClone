package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.databinding.ComponentProductBinding

class ProductComponentAdapter(
    private val adapter: ProductAdapter
) : RecyclerView.Adapter<ProductComponentAdapter.ProductComponentViewHolder>() {

    inner class ProductComponentViewHolder(
        private val binding: ComponentProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(adapter: ProductAdapter) {
            binding.recyclerViewGoodDeal.layoutManager = GridLayoutManager(binding.root.context, 2)
            binding.recyclerViewGoodDeal.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductComponentViewHolder {
        return ProductComponentViewHolder(
            ComponentProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductComponentViewHolder, position: Int) {
        holder.bindData(adapter)
    }

    override fun getItemCount() = 1
}