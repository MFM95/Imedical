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
import com.example.imedical.home.presentation.viewmodel.OffersViewModel
import kotlinx.android.synthetic.main.offers_fragment.*
import javax.inject.Inject

class OffersFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<OffersViewModel>
    private lateinit var viewModel: OffersViewModel

    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OffersViewModel::class.java)
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
    }

    private fun setupRecyclerView(){
        offersRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        adapter = ProductsAdapter(ArrayList(), ProductCallback(), activity!!)
        offersRecyclerView.adapter = adapter
    }

    private fun subscribeViewModel(){
        if(!viewModel.getOffers().hasObservers()){
            viewModel.getOffers().observe(this, Observer { models ->
                adapter.products.addAll(models!!)
                offersProgressBar.visibility = View.GONE
                adapter.notifyDataSetChanged()
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
    }

    inner class ProductCallback : IProductCallback{
        override fun onWishClick(id: Int, index: Int) {
            if(!this@OffersFragment.adapter.products[index].inWishList)
                this@OffersFragment.viewModel.addWish(id, index)
            else this@OffersFragment.viewModel.removeWish(id, index)
        }

        override fun onCompareClick(id: Int) {
        }

        override fun addToCart(id: Int) {
        }

        override fun onProductClick(productModel: ProductModel) {
        }

    }
}
