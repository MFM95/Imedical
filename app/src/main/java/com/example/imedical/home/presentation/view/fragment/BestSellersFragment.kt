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
import com.example.imedical.core.model.ProductModel
import com.example.imedical.home.presentation.view.activity.HomeActivity
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

        adapter = ProductsAdapter(ArrayList(), productCallback, activity!!)
        bestSellersRecyclerView.adapter = adapter

    }

    private fun subscribeViewModel(){
        if(!viewModel.getBestSellers().hasObservers()){
            viewModel.getBestSellers().observe(this, Observer { models ->
                bestSellersProgressBar.visibility = View.GONE
                if(models?.status == true) {
                    adapter.products.addAll(models.data!!)
                    adapter.notifyDataSetChanged()
                } else{
                    bestSellersLabel.visibility = View.GONE
                    showMessage(models?.error)
                }
            })
            viewModel.updateBestSellers()

        } else viewModel.updateBestSellers()

        if(!this.viewModel.getWishLiveData().hasObservers())
            this.viewModel.getWishLiveData().observe(this, Observer {
                    dataWrapper ->
                if(dataWrapper != null && dataWrapper.status && dataWrapper.data != null) {
                    this.adapter.products[dataWrapper.data].inWishList =
                        !this.adapter.products[dataWrapper.data].inWishList
                    this.adapter.notifyItemChanged(dataWrapper.data)
                } else showMessage(dataWrapper?.error)
            })
        productViewModel.getAddToCartLiveData().observe(this, Observer {
            hideProgress()
            if(it != null ){
                if(!it.status)
                    showMessage(it.error)
                else {
                    if(activity is HomeActivity)
                        (activity as HomeActivity).updateCartLabel()
                    showMessage("Item added to cart")
                }
            }
        })
    }

    private val productCallback = object : IProductCallback{
        override fun onCompareClick(productModel: ProductModel) {
            compareViewModel.addToCompareList(productModel)
        }

        override fun onWishClick(id: Int, index: Int) {
            if(!this@BestSellersFragment.adapter.products[index].inWishList)
                this@BestSellersFragment.viewModel.addWish(id, index)
            else this@BestSellersFragment.viewModel.removeWish(id, index)
        }


        override fun addToCart(id: Int) {
            showProgress()
            productViewModel.addToCart(id, 1)
        }

        override fun onProductClick(productModel: ProductModel) {
        }

    }


    private fun observeOnCompareClick() {
        adapter?.let {
            it.onCompareClick.observe(this, Observer { model ->
                model?.let { productModel ->
                    compareViewModel.addToCompareList(productModel)
                    compareViewModel.getAddLiveData().observe(this, Observer {
                        showSnack(getString(R.string.added_to_compare_message))
                    })

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

//    private fun mapProductModel(model: ProductModel): ProductModel {
//        return ProductModel(
//            model.id,
//            model.name,
//            model.imageUrl,
//            model.price,
//            model.salePrice,
//            model.inWishList,
//            model.inCompareList,
//            model.brand,
//            model.quantity
//        )
//    }


}
