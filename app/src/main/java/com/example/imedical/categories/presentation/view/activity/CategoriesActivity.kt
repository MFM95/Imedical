package com.example.imedical.categories.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.imedical.R
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.presentation.view.adapter.CategoriesAdapter
import com.example.imedical.categories.presentation.viewmodel.CategoriesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.activity_categories.*
import javax.inject.Inject

class CategoriesActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CategoriesViewModel>
    lateinit var viewModel: CategoriesViewModel

    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categoriesList = ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_categories)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

        getCategories()
    }

    private fun getCategories() {
        viewModel.getAllCategories()
        viewModel.getCategoriesLiveData().observe(this, Observer {
            it?.let {
                categoriesList = it
                setUpCategoriesRecyclerView()
             }
        })

    }

    private fun setUpCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter(categoriesList, this)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategories.adapter = categoriesAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, CategoriesActivity::class.java)
    }
}
