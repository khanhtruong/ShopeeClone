package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.databinding.ItemToolBinding

interface ToolBannerInteraction {
    fun firstFeature()
    fun secondFeature()
    fun thirdFeature()
    fun fourthFeature()
    fun more()
}

class ToolBannerAdapter(private val listener: ToolBannerInteraction) :
    RecyclerView.Adapter<ToolBannerAdapter.ToolBannerViewHolder>() {

    inner class ToolBannerViewHolder(private val binding: ItemToolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listener: ToolBannerInteraction) {
            binding.linearLayoutFirstTool.setOnClickListener {
                listener.firstFeature()
            }

            binding.linearLayoutSecondTool.setOnClickListener {
                listener.secondFeature()
            }

            binding.linearLayoutThirdTool.setOnClickListener {
                listener.thirdFeature()
            }

            binding.linearLayoutFourthTool.setOnClickListener {
                listener.fourthFeature()
            }

            binding.linearLayoutMoreTool.setOnClickListener {
                listener.more()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolBannerViewHolder {
        return ToolBannerViewHolder(
            ItemToolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToolBannerViewHolder, position: Int) {
        holder.bindData(listener)
    }

    override fun getItemCount() = 1
}