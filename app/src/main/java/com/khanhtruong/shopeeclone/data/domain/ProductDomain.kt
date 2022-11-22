package com.khanhtruong.shopeeclone.data.domain

import com.google.gson.annotations.SerializedName
import com.khanhtruong.shopeeclone.data.model.ProductData

data class ProductDomain(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("Snapshot")
    var snapshot: Int?,
    @SerializedName("Price")
    var price: Double?,
    @SerializedName("Sold")
    var sold: Double?,
    @SerializedName("Title")
    var title: String?,
) {

    fun toProductData(): ProductData {
        return ProductData(
            this.id ?: 0,
            this.snapshot ?: 0,
            this.price ?: 0.0,
            this.sold ?: 0.0,
            this.title ?: "",
        )
    }
}
