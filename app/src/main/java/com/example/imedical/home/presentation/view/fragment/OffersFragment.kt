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
import com.example.imedical.home.presentation.viewmodel.OffersViewModel
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.offers_fragment.*
import javax.inject.Inject

class OffersFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<OffersViewModel>
    private lateinit var viewModel: OffersViewModel

    @Inject
    lateinit var productViewModelFactory: ViewModelFactory<ProductViewModel>
    private val productViewModel by lazy { ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java) }

    private lateinit var adapter: ProductsAdapter

    lateinit var compareViewModel: CompareListViewModel

    @Inject
    lateinit var compareViewModelFactory: ViewModelFactory<CompareListViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OffersViewModel::class.java)
        compareViewModel = ViewModelProviders.of(this, compareViewModelFactory).get(CompareListViewModel::class.java)
        subscribeViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offers_fragment, container, false)
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
        offersRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        adapter = ProductsAdapter(ArrayList(), productCallback, activity!!)
        offersRecyclerView.adapter = adapter
    }

    private fun subscribeViewModel(){
        if(!viewModel.getOffers().hasObservers()){
            viewModel.getOffers().observe(this, Observer { models ->
                if(models?.status == true) {
                    adapter.products.addAll(models.data!!)
                    offersProgressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                } else showMessage(models?.error)
            })
            viewModel.updateOffers()

        } else viewModel.updateOffers()

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
            if(!this@OffersFragment.adapter.products[index].inWishList)
                this@OffersFragment.viewModel.addWish(id, index)
            else this@OffersFragment.viewModel.removeWish(id, index)
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
