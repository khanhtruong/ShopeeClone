package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.data.model.ProductData
import com.khanhtruong.shopeeclone.databinding.ComponentProductBinding
import com.khanhtruong.shopeeclone.util.ConcatenableAdapter

class ProductHeaderAdapter(
    override val concatAdapterIndex: Int,
    private val totalSpanSize: Int,
) : RecyclerView.Adapter<ProductHeaderAdapter.ProductHeaderViewHolder>(), ConcatenableAdapter {

    inner class ProductHeaderViewHolder(
        private val binding: ComponentProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() = Unit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHeaderViewHolder {
        return ProductHeaderViewHolder(
            ComponentProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductHeaderViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int): Int {
        return globalViewItemType()
    }

    override fun spanSizeByType(globalItemViewType: Int): Int {
        return totalSpanSize
    }
}