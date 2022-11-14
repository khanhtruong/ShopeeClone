package com.khanhtruong.shopeeclone.view

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ToolbarWidgetWrapper
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.adapter.*
import com.khanhtruong.shopeeclone.base.BaseFragment
import com.khanhtruong.shopeeclone.data.getFeatureBanners
import com.khanhtruong.shopeeclone.data.getFirstDeals
import com.khanhtruong.shopeeclone.data.getProductDataset
import com.khanhtruong.shopeeclone.databinding.FragmentHomeBinding

class FragmentHome : BaseFragment<FragmentHomeBinding>(), WalletBannerInteraction,
    ToolBannerInteraction {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private lateinit var contentAdapter: ConcatAdapter
    private lateinit var featureBannerAdapter: FeatureBannerAdapter
    private lateinit var walletBannerAdapter: WalletBannerAdapter
    private lateinit var toolBannerAdapter: ToolBannerAdapter
    private lateinit var newMemberGiftAdapter: NewMemberGiftAdapter
    private lateinit var firstDealAdapter: FirstDealAdapter
    private lateinit var productComponentAdapter: ProductComponentAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContentAdapter()
    }

    private fun setupContentAdapter() {
        contentAdapter =
            ConcatAdapter(ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build())
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewContent.adapter = contentAdapter

        listeningContentScrolling()

        featureBannerAdapter = FeatureBannerAdapter {
            toast("Open banner ${it.title}")
        }
        featureBannerAdapter.submitList(getFeatureBanners())
        contentAdapter.addAdapter(FeatureBannerComponentAdapter(featureBannerAdapter))

        walletBannerAdapter = WalletBannerAdapter(this)
        contentAdapter.addAdapter(walletBannerAdapter)

        toolBannerAdapter = ToolBannerAdapter(this)
        contentAdapter.addAdapter(toolBannerAdapter)

        firstDealAdapter = FirstDealAdapter {
            toast("Open first deal")
        }
        newMemberGiftAdapter = NewMemberGiftAdapter(firstDealAdapter)
        firstDealAdapter.submitList(getFirstDeals())
        contentAdapter.addAdapter(newMemberGiftAdapter)

        productAdapter = ProductAdapter {
            toast("Open product: ${it.title}")
        }
        productComponentAdapter = ProductComponentAdapter(productAdapter)
        productAdapter.submitList(getProductDataset())
        contentAdapter.addAdapter(productComponentAdapter)
    }

    private fun listeningContentScrolling() {
        binding.recyclerViewContent.viewTreeObserver.addOnScrollChangedListener {
            val yOffset = binding.recyclerViewContent.computeVerticalScrollOffset()
            var alpha = 255
            var faction = 0f
            if (yOffset in 0..255) {
                alpha = yOffset
                faction = ((yOffset * 100).toFloat() / 255) / 100
            } else {
                alpha = 255
                faction = 1f
            }
            // Transparent background
            val white = ResourcesCompat.getColor(resources, R.color.white, null)
            val result = ColorUtils.setAlphaComponent(white, alpha)
            binding.appBarLayoutToolbar.setBackgroundColor(result)

            // Transition button color between white and primary colors
            val primaryColor = ResourcesCompat.getColor(resources, R.color.primary, null)
            val colorButton = ColorUtils.blendARGB(white, primaryColor, faction)
            binding.toolBar.imageViewChatButton.setColorFilter(colorButton)
            binding.toolBar.imageViewShoppingCar.setColorFilter(colorButton)

            // Transition search background color between white and gray colors
            val subColor =
                ResourcesCompat.getColor(resources, R.color.background_search_field_inactive, null)
            val searchBGColor = ColorUtils.blendARGB(white, subColor, faction)
            binding.toolBar.textViewSearchField.setBackgroundColor(searchBGColor)
        }
    }

    override fun openQRScanner() {
        toast("Open QR Scanner")
    }

    override fun openShopeePay() {
        toast("Open shopee pay")
    }

    override fun openShopeeCoin() {
        toast("Open shopee xu")
    }

    override fun firstFeature() {
        toast("Gì cũng Rẻ-Mua Là Freeship")
    }

    override fun secondFeature() {
        toast("Mã Giảm Giá")
    }

    override fun thirdFeature() {
        toast("Miễn Phí Vận Chuyển")
    }

    override fun fourthFeature() {
        toast("ShopeeFood - Giao Thực Phẩm")
    }

    override fun more() {
        toast("Xem thêm")
    }
}