package com.example.imedical.home.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.presentation.view.adapter.IProductCallback
import com.example.imedical.home.presentation.view.adapter.ProductsAdapter
import com.example.imedical.home.presentation.viewmodel.BestSellersViewModel
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.best_sellers_fragment.*
import javax.inject.Inject

class BestSellersFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<BestSellersViewModel>
    private lateinit var viewModel: BestSellersViewModel

    private lateinit var adapter: ProductsAdapter

    @Inject
    lateinit var productViewModelFactory: ViewModelFactory<ProductViewModel>
    private val productViewModel by lazy { ViewModelProviders.of(this, productViewModelFactory).get(
        ProductViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BestSellersViewModel::class.java)
        subscribeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.best_sellers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        bestSellersRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        adapter = ProductsAdapter(ArrayList(), productCallback, activity!!)
        bestSellersRecyclerView.adapter = adapter

    }

    private fun subscribeViewModel(){
        if(!viewModel.getBestSellers().hasObservers()){
            viewModel.getBestSellers().observe(this, Observer { models ->
                adapter.products.addAll(models!!)
                bestSellersProgressBar.visibility = View.GONE
                adapter.notifyDataSetChanged()
            })
            viewModel.updateBestSellers()

        } else viewModel.updateBestSellers()

        productViewModel.getAddToCartLiveData().observe(this, Observer {
            if(it != null ){
                if(!it.status)
                    showMessage(it.error)
                else showMessage("Item added to cart")
            }
        })
    }

    private val productCallback = object : IProductCallback{
        override fun onWishClick(id: Int) {
        }

        override fun onCompareClick(id: Int) {
        }

        override fun addToCart(id: Int) {
            productViewModel.addToCart(id, 1)
        }

        override fun onProductClick(productModel: ProductModel) {
        }

    }
}
