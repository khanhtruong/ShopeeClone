package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.databinding.ItemNewMemberGiftBinding

class NewMemberGiftAdapter(
    private val adapter: FirstDealAdapter
) : RecyclerView.Adapter<NewMemberGiftAdapter.NewMemberGiftViewHolder>() {

    inner class NewMemberGiftViewHolder(private val binding: ItemNewMemberGiftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(adapter: FirstDealAdapter) {
            binding.recyclerViewFirstDeal.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewFirstDeal.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMemberGiftViewHolder {
        return NewMemberGiftViewHolder(
            ItemNewMemberGiftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewMemberGiftViewHolder, position: Int) {
        holder.bindData(adapter)
    }

    override fun getItemCount() = 1
}