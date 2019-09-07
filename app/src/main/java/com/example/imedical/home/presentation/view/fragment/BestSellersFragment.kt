package com.example.imedical.home.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.compare.presentation.viewmodel.CompareListViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.domain.model.ProductModel
import com.example.imedical.home.presentation.view.adapter.IProductCallback
import com.example.imedical.home.presentation.view.adapter.ProductsAdapter
import com.example.imedical.home.presentation.viewmodel.BestSellersViewModel
import kotlinx.android.synthetic.main.best_sellers_fragment.*
import javax.inject.Inject

class BestSellersFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<BestSellersViewModel>
    private lateinit var viewModel: BestSellersViewModel

    private lateinit var adapter: ProductsAdapter

    lateinit var compareViewModel: CompareListViewModel

    @Inject
    lateinit var compareViewModelFactory: ViewModelFactory<CompareListViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BestSellersViewModel::class.java)
        compareViewModel = ViewModelProviders.of(this, compareViewModelFactory).get(CompareListViewModel::class.java)
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
        observeOnCompareClick()
        observeOnAddToCartClick()
        observeOnProductClick()
        observeOnWishClick()
    }

    private fun setupRecyclerView(){
        bestSellersRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        adapter = ProductsAdapter(ArrayList(), ProductCallback(), activity!!)
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
    }

    class ProductCallback : IProductCallback {
        override fun onWishClick(id: Int) {
        }

        override fun onCompareClick(productModel: ProductModel) {
        }

        override fun addToCart(id: Int) {
        }

        override fun onProductClick(productModel: ProductModel) {
        }

    }


    private fun observeOnCompareClick() {
        adapter?.let {
            it.onCompareClick.observe(this, Observer { model ->
                model?.let { productModel ->
                    compareViewModel.addToCompareList(mapProductModel(productModel))
                }
            })
        }
    }

    private fun observeOnWishClick() {
        adapter?.let {
            it.onWishClick.observe(this, Observer {

            })
        }
    }

    private fun observeOnAddToCartClick() {
        adapter?.let {
            it.onAddToCartClick.observe(this, Observer {

            })
        }
    }

    private fun observeOnProductClick() {
        adapter?.let {
            it.onAddToCartClick.observe(this, Observer {

            })
        }
    }

    private fun mapProductModel(model: ProductModel): com.example.imedical.compare.domain.model.ProductModel {
        return com.example.imedical.compare.domain.model.ProductModel(
            model.id,
            model.name,
            model.imageUrl,
            model.price,
            model.salePrice,
            model.inWishList,
            model.inCompareList,
            model.brand,
            model.quantity
        )
    }


}
