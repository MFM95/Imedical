package com.example.imedical.wishlist.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import com.example.imedical.wishlist.presentation.view.adapter.WishesAdapter
import com.example.imedical.wishlist.presentation.view.adapter.callback.IWishCallback
import com.example.imedical.wishlist.presentation.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.wish_list_fragment.*
import javax.inject.Inject

class WishListFragment : BaseFragment() {


    @Inject lateinit var viewModelFactory: ViewModelFactory<WishListViewModel>
    private lateinit var viewModel: WishListViewModel
    private lateinit var adapter: WishesAdapter

    @Inject
    lateinit var productViewModelFactory: ViewModelFactory<ProductViewModel>
    private val productViewModel by lazy { ViewModelProviders.of(this, productViewModelFactory).get(
        ProductViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WishListViewModel::class.java)
        subscribeViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wish_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        activity?.setTitle(R.string.wish_list)
    }

    private fun subscribeViewModel(){
        if(!viewModel.getWithListLiveData().hasObservers()){
            viewModel.getWithListLiveData().observe(this, Observer { models ->
                adapter.wishes.addAll(models!!)
                wishListProgressBar.visibility = View.GONE
                adapter.notifyDataSetChanged()
            })
            viewModel.updateWishList()

        } else viewModel.updateWishList()

        productViewModel.getAddToCartLiveData().observe(this, Observer {
            if(it != null ){
                if(!it.status)
                    showMessage(it.error)
                else showMessage("Item added to cart")
            }
        })

    }

    private fun setupRecyclerView(){
        wishListRecyclerView.layoutManager =
            GridLayoutManager(activity, 2)

        adapter = WishesAdapter(ArrayList(), wishCallback , activity!!)
        wishListRecyclerView.adapter = adapter
    }

    private val wishCallback = object : IWishCallback{
        override fun onProductClick(productModel: ProductModel) {

        }

        override fun onRemoveClick(id: Int, index: Int) {
        }

        override fun onCompareClick(id: Int) {
        }

        override fun addToCart(id: Int) {
            productViewModel.addToCart(id, 1)
        }

    }
}
