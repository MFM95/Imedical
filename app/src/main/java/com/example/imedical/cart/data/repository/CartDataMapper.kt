package com.example.imedical.cart.data.repository

import com.example.imedical.cart.data.entity.CartResponse
import com.example.imedical.cart.domain.model.CartItemModel
import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.core.api.ApiResponse
import com.example.imedical.core.model.DataWrapper

object CartDataMapper {
    fun mapCartResponse(apiResponse: ApiResponse<CartResponse>?): DataWrapper<CartModel>{
        if(apiResponse?.data == null){
            return DataWrapper(false, null, apiResponse?.error?:"Server Error")
        }
        val items = ArrayList<CartItemModel>()
        for(entity in apiResponse.data.cartItems)
            items.add(CartItemModel(entity.cover, entity.id, entity.name, entity.price, entity.quantity, entity.rowId))

        return DataWrapper(apiResponse.status?:false,
            CartModel(items, apiResponse.data.shippingFee, apiResponse.data.subtotal, apiResponse.data.tax, apiResponse.data.total)
            ,apiResponse.error)
    }
}