package com.example.imedical.categories.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.categories.domain.interactor.GetCategoriesUseCase
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase) : ViewModel()  {


    private val categoriesLiveData: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()

    fun getCategoriesLiveData(): MutableLiveData<ArrayList<CategoryModel>>{
        return categoriesLiveData
    }

    fun getAllCategories() {
        getCategoriesUseCase.execute(Unit, onResult = this::onGetCategoriesResult)
    }

    private fun onGetCategoriesResult(dataWrapper: DataWrapper<ArrayList<CategoryModel>>){
        categoriesLiveData.value = dataWrapper.data
    }

}