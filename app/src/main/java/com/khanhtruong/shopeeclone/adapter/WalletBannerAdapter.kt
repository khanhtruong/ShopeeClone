package com.khanhtruong.shopeeclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khanhtruong.shopeeclone.databinding.ItemWalletBannerBinding
import com.khanhtruong.shopeeclone.util.ConcatenableAdapter

interface WalletBannerInteraction {
    fun openQRScanner()
    fun openShopeePay()
    fun openShopeeCoin()
}

class WalletBannerAdapter(
    override val concatAdapterIndex: Int,
    private val totalSpanSize: Int,
    private val interaction: WalletBannerInteraction,
) : RecyclerView.Adapter<WalletBannerAdapter.WalletBannerViewHolder>(), ConcatenableAdapter {

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

    override fun getItemViewType(position: Int): Int {
        return globalViewItemType()
    }

    override fun spanSizeByType(globalItemViewType: Int): Int {
        return totalSpanSize
    }
}