package com.example.imedical.wishlist.domain.usecase

import com.example.imedical.core.model.DataWrapper
import com.example.imedical.core.usecase.BaseUseCase
import com.example.imedical.wishlist.domain.repository.IWishedRepository
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 9/21/2019.
 */
class RemoveWishUseCase @Inject constructor(private val iWishedRepository: IWishedRepository):
    BaseUseCase<DataWrapper<Int>, RemoveWishUseCase.RemoveWishParams>() {

    override suspend fun run(params: RemoveWishParams): DataWrapper<Int> {
        return iWishedRepository.removeWish(params.id, params.index)
    }

    data class RemoveWishParams(val id: Int, val index: Int)
}