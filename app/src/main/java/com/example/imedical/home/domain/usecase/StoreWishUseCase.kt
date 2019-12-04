package com.example.imedical.home.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.home.domain.repository.IProductRepository
import com.example.imedical.wishlist.domain.repository.IWishedRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 9/8/2019.
 */
class StoreWishUseCase @Inject constructor(private val iProductRepository: IProductRepository):
    BaseUseCase<DataWrapper<Int>, StoreWishUseCase.AddWishParams>() {

    override suspend fun run(params: AddWishParams): DataWrapper<Int> {
        return iProductRepository.storeWish(params.id, params.index)
    }

    data class AddWishParams(val id: Int, val index: Int)
}