package com.example.imedical.categories.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.imedical.categories.domain.interactor.GetCategoriesUseCase
import com.example.imedical.categories.domain.interactor.GetCategoryChildrenUseCase
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.core.model.DataWrapper
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryChildrenUseCase: GetCategoryChildrenUseCase) : ViewModel()  {


    private val categoriesLiveData: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()
    private val categoryChildrenLiveData: MutableLiveData<Pair<Int, ArrayList<CategoryModel>>> = MutableLiveData()

    fun getCategoriesLiveData(): MutableLiveData<ArrayList<CategoryModel>>{
        return categoriesLiveData
    }

    fun getAllCategories() {
        getCategoriesUseCase.execute(Unit, onResult = this::onGetCategoriesResult)
    }

    private fun onGetCategoriesResult(dataWrapper: DataWrapper<ArrayList<CategoryModel>>){
        categoriesLiveData.value = dataWrapper.data
    }

    fun getCategoryChildrenLiveData(): MutableLiveData<Pair<Int, ArrayList<CategoryModel>>>{
        return categoryChildrenLiveData
    }

    fun getCategoryChildren(id: Int) {
        getCategoryChildrenUseCase.execute(id, onResult = this::onGetCategoryChildrenResult)
    }

    private fun onGetCategoryChildrenResult(result: Pair<Int, DataWrapper<ArrayList<CategoryModel>>>){
        categoryChildrenLiveData.value = Pair(result.first, result.second.data!!)
    }
}