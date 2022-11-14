package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.databinding.ItemWalletBannerBinding

interface WalletBannerInteraction {
    fun openQRScanner()
    fun openShopeePay()
    fun openShopeeCoin()
}

class WalletBannerAdapter(
    private val interaction: WalletBannerInteraction
) : RecyclerView.Adapter<WalletBannerAdapter.WalletBannerViewHolder>() {

    inner class WalletBannerViewHolder(private val binding: ItemWalletBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(interaction: WalletBannerInteraction) {
            binding.imageViewQRButton.setOnClickListener {
                interaction.openQRScanner()
            }
            binding.clShopeePayButton.setOnClickListener {
                interaction.openShopeePay()
            }
            binding.imageViewShopeeCoin.setOnClickListener {
                interaction.openShopeeCoin()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletBannerViewHolder {
        return WalletBannerViewHolder(
            ItemWalletBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WalletBannerViewHolder, position: Int) {
        holder.bindData(interaction)
    }

    override fun getItemCount() = 1
}