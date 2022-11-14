package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.data.model.FirstDealData
import com.khanhtruong.shopeeclone.databinding.ItemFirstDealBinding

class FirstDealAdapter(
    private val onClick: (FirstDealData) -> Unit
) : ListAdapter<FirstDealData, FirstDealAdapter.FirstDealViewHolder>(FirstDealData.FirstDealDiffCallBack()) {

    inner class FirstDealViewHolder(
        private val binding: ItemFirstDealBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FirstDealData, onClick: (FirstDealData) -> Unit) {
            binding.imageViewFirstDeal.setImageResource(data.image)
            binding.textViewFirstDeal.text = data.description
            binding.root.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstDealViewHolder {
        return FirstDealViewHolder(
            ItemFirstDealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FirstDealViewHolder, position: Int) {
        holder.bindData(currentList[position], onClick)
    }
}