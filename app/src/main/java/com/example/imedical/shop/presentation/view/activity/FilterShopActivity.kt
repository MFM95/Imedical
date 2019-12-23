package com.example.imedical.shop.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.data.entity.BrandEntity
import com.example.imedical.shop.presentation.view.adapter.BrandsAdapter
import com.example.imedical.shop.presentation.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.activity_filter_shop.*
import javax.inject.Inject

class FilterShopActivity : BaseActivity() {

    @Inject lateinit var filterViewModelFactory: ViewModelFactory<FilterViewModel>
    private val filterViewModel by lazy {
        ViewModelProviders.of(this, filterViewModelFactory).get(FilterViewModel::class.java)
    }

    private val selectedBrands = ArrayList<Int>()
    private val brandsAdapter by lazy { BrandsAdapter(ArrayList(), this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_filter_shop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(intent.getIntArrayExtra("brands") != null)
            for (brand in intent.getIntArrayExtra("brands"))
                selectedBrands.add(brand)

        subscribeViewModel()
        showProgress()
        filterViewModel.getBrands()
        initRecyclerView()
        setDoneListener()
    }

    private fun subscribeViewModel(){
        filterViewModel.brandsLiveData.observe(this, Observer {
            hideProgress()
            if(it?.status == true){
                if(it.data != null && it.data.isNotEmpty()) {
                    for (b in it.data)
                        if(selectedBrands.contains(b.id))
                            b.selected = true
                    brandsAdapter.brands.addAll(it.data)
                    brandsAdapter.notifyDataSetChanged()
                    return@Observer
                }
            } else showMessage(it?.error)
            tvFilterBrandTitle.visibility = View.GONE
        })
    }

    private fun initRecyclerView(){
        rvBrandsFilter.adapter = brandsAdapter
        rvBrandsFilter.layoutManager = GridLayoutManager(this, 2)

        brandsAdapter.brandClickedLiveData.observe(this, Observer {
            if(it != null)
                if(selectedBrands.contains(it.id))
                    selectedBrands.remove(it.id)
                else selectedBrands.add(it.id)
        })
    }

    private fun setDoneListener(){
        btnFilter.setOnClickListener {
            val intent = Intent()
            if(selectedBrands.isNotEmpty())
                intent.putIntegerArrayListExtra("brands", selectedBrands)
            if(etMinPrice.text.isNotEmpty())
                intent.putExtra("min_price", etMinPrice.text.toString().toDoubleOrNull())
            if(etMaxPrice.text.isNotEmpty())
                intent.putExtra("max_price", etMaxPrice.text.toString().toDoubleOrNull())
            setResult(1, intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
