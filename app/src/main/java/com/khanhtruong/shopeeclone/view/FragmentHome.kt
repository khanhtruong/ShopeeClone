package com.khanhtruong.shopeeclone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.map
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.adapter.*
import com.khanhtruong.shopeeclone.base.BaseFragment
import com.khanhtruong.shopeeclone.data.getFeatureBanners
import com.khanhtruong.shopeeclone.data.getFirstDeals
import com.khanhtruong.shopeeclone.data.getProductDataset
import com.khanhtruong.shopeeclone.databinding.FragmentHomeBinding
import com.khanhtruong.shopeeclone.util.ConcatenableAdapter
import com.khanhtruong.shopeeclone.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
    private lateinit var productAdapter: ProductAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupContentAdapter()
        initViewModel()
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productItems
                    .collectLatest {
                        productAdapter.submitData(it)
                    }
            }
        }
    }

    private fun setupContentAdapter() {
        val gridSpanSize = 2

        contentAdapter =
            ConcatAdapter(
                ConcatAdapter.Config.Builder()
                    .setIsolateViewTypes(false)
                    .build()
            )

        listeningContentScrolling()

        // Feature banner section
        featureBannerAdapter = FeatureBannerAdapter {
            toast("Open banner ${it.title}")
        }
        featureBannerAdapter.submitList(getFeatureBanners())
        contentAdapter.addAdapter(
            FeatureBannerComponentAdapter(
                0,
                gridSpanSize,
                featureBannerAdapter
            )
        )

        // Wallet banner section
        walletBannerAdapter = WalletBannerAdapter(1, gridSpanSize, this)
        contentAdapter.addAdapter(walletBannerAdapter)

        // Tool banner section
        toolBannerAdapter = ToolBannerAdapter(2, gridSpanSize, this)
        contentAdapter.addAdapter(toolBannerAdapter)

        // First deal section
        firstDealAdapter = FirstDealAdapter {
            toast("Open first deal")
        }
        newMemberGiftAdapter = NewMemberGiftAdapter(3, gridSpanSize, firstDealAdapter)
        firstDealAdapter.submitList(getFirstDeals())
        contentAdapter.addAdapter(newMemberGiftAdapter)

        // Product section
        // Header
        val productHeaderAdapter = ProductHeaderAdapter(4, gridSpanSize)
        contentAdapter.addAdapter(productHeaderAdapter)
        // List products
        productAdapter = ProductAdapter(5) {
            toast("Open product: ${it.title}")
        }
        contentAdapter.addAdapter(productAdapter)

        val layoutManager = GridLayoutManager(requireContext(), gridSpanSize)
        binding.recyclerViewContent.layoutManager = layoutManager
        binding.recyclerViewContent.adapter = contentAdapter

        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val globalItemViewType = contentAdapter.getItemViewType(position)
                val spanSize: Int = contentAdapter
                    .adapters
                    .filterIsInstance<ConcatenableAdapter>()
                    .first {
                        it.hasGlobalViewItemType(globalItemViewType)
                    }
                    .spanSizeByType(globalItemViewType)
                return spanSize
            }
        }
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

            // Transition buttons color between white and primary colors
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