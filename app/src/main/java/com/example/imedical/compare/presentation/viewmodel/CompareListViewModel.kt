package com.example.imedical.compare.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.compare.domain.interactor.AddToCompareListUseCase
import com.example.imedical.compare.domain.interactor.GetCompareListUseCase
import com.example.imedical.compare.domain.model.ProductModel
import javax.inject.Inject

class CompareListViewModel @Inject constructor(private val getCompareListUseCase: GetCompareListUseCase,
                                               private val addToCompareListUseCase: AddToCompareListUseCase)
    : ViewModel(){
    private val compareListLiveData: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()

    fun getCompareListLiveData(): LiveData<ArrayList<ProductModel>> {
        return compareListLiveData
    }

    fun getCompareList() {
        getCompareListUseCase.execute(Unit, onResult = this::onGetCompareListResult)
    }

    private fun onGetCompareListResult(result: ArrayList<ProductModel>) {
        compareListLiveData.value = result
    }

    fun addToCompareList(product: ProductModel) {
        addToCompareListUseCase.execute(product, this::onAddToCompareListResult)
    }

    private fun onAddToCompareListResult(result: Unit) {

    }

}