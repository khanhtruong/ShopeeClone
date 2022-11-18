package com.khanhtruong.shopeeclone.data

import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.model.BannerData
import com.khanhtruong.shopeeclone.data.model.FirstDealData
import com.khanhtruong.shopeeclone.data.model.ProductData

fun getFeatureBanners(): List<BannerData> {
    return listOf(
        BannerData("Ở nhà không khó", R.drawable.banner_o_nha_khong_kho),
        BannerData("Sale 5.5", R.drawable.banner_sale_5_5),
        BannerData("Siêu sale 22.9", R.drawable.banner_sieu_sale_22_9),
        BannerData("Sale tết", R.drawable.banner_tet_sale),
    )
}

fun getFirstDeals(): List<FirstDealData> {
    return listOf(
        FirstDealData(R.drawable.ic_first_deal_1, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_2, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_3, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_2, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_3, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_1, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_3, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_2, "Giá 0Đ"),
        FirstDealData(R.drawable.ic_first_deal_2, "Giá 0Đ"),
    )
}

fun getProductDataset(): List<ProductData> {
    return listOf(
        ProductData(1, R.drawable.img_product_1, 10000.0, 20.0, "Ring"),
        ProductData(2, R.drawable.img_product_2, 20000.0, 2.0, "Nail"),
        ProductData(3, R.drawable.img_product_3, 30000.0, 10.0, "T-Shirt"),
        ProductData(4, R.drawable.img_product_4, 40000.0, 1.0, "Lego"),
        ProductData(5, R.drawable.img_product_5, 60000.0, 4.0, "Necklace"),
        ProductData(6, R.drawable.img_product_6, 100000.0, 90.0, "Shirt"),
        ProductData(7, R.drawable.img_product_7, 220000.0, 101.0, "Light"),
        ProductData(8, R.drawable.img_product_2, 1000000.0, 120.0, "Nail"),
        ProductData(9, R.drawable.img_product_5, 90000.0, 22.0, "Necklace"),
        ProductData(10, R.drawable.img_product_1, 300000.0, 15.0, "Ring"),
        ProductData(11, R.drawable.img_product_3, 40000.0, 5.0, "T-Shirt"),
        ProductData(12, R.drawable.img_product_4, 80000.0, 89.0, "Lego"),
    )
}