package com.example.imedical.wishlist.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.wishlist.domain.repository.IWishedRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 9/7/2019.
 */
class WishListUseCase @Inject constructor(private val iWishedRepository: IWishedRepository)
    : BaseUseCase<DataWrapper<List<ProductModel>>, Unit>(){

    override suspend fun run(params: Unit):  DataWrapper<List<ProductModel>>{
        return iWishedRepository.getWishList()
    }
}