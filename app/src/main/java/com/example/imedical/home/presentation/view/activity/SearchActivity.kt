package com.example.imedical.home.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.example.imedical.R
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import com.example.imedical.shop.presentation.view.activity.FilterShopActivity
import com.example.imedical.shop.presentation.view.adapter.ShopAdapter
import com.example.imedical.shop.presentation.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.rvShop
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ShopViewModel>
    private val shopViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        ShopViewModel::class.java) }
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var shopAdapter: ShopAdapter
    private var endOfData: Boolean = false
    private var isLoading: Boolean = false
    private var query: String? = null
    private var category: Int? = null
    private var minPrice: Double? = null
    private var maxPrice: Double? = null
    private var brands: ArrayList<Int>? = null
    private var orderBy: String? = null
    private var asc: Boolean? = null

    @Inject lateinit var productViewModelFactory: ViewModelFactory<ProductViewModel>
    private val productViewModel by lazy {
        ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        subscribeViewModel()
        initRecycler()
        listenToProductsActions()
        listenToTextChanges()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private fun listenToTextChanges(){
        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                query = searchEditText.text.toString()
                reloadData()
            }
        })
    }

    private fun subscribeViewModel(){
        shopViewModel.shopLiveData.observe(this, Observer {
            removeLoading()
            if(it?.status == true && it.data != null){
                if(it.data.isNotEmpty()) {
                    addItems(it.data)
                } else endOfData = true
            }
        })

        productViewModel.getWishLiveData().observe(this, Observer {
            hideProgress()
            if(it?.status == true) {
                if (it.data != null && shopAdapter.products[it.data]?.inWishList == true)
                    showMessage("Added to wishlist")
                else showMessage("Removed from wishlist")
            }
            else showMessage(it?.error)
        })

        productViewModel.getAddCompareLiveData().observe(this, Observer {
            hideProgress()
            showMessage("Added to your compare list")
        })

        productViewModel.getAddToCartLiveData().observe(this, Observer {
            hideProgress()
            if(it?.status == true) {
                showMessage("Added to cart successfully")
            } else showMessage(it?.error)
        })
    }
    private fun addItems(items: List<ProductModel>){
        if(shopAdapter.products.size == 0)
            addRecyclerScrollListener()
        val initIndex = shopAdapter.products.size
        shopAdapter.products.addAll(items)
        shopAdapter.notifyItemRangeInserted(initIndex, items.size)
    }

    private fun addLoading(){
        isLoading = true
        shopAdapter.products.add(null)
        shopAdapter.notifyItemInserted(shopAdapter.products.size - 1)
    }

    private fun removeLoading(){
        isLoading = false
        if(shopAdapter.products.size == 0)
            return
        val index = shopAdapter.products.size - 1
        shopAdapter.products.removeAt(index)
        shopAdapter.notifyItemRemoved(index)
    }
    private fun initRecycler(){
        val spans = if(shopViewModel.shopGridArrange.value == true) 2 else 1
        gridLayoutManager = GridLayoutManager(this, spans)
        rvShop.layoutManager = gridLayoutManager
        shopAdapter = ShopAdapter(arrayListOf(), this)
        rvShop.adapter = shopAdapter
    }

    private fun addRecyclerScrollListener(){
        rvShop.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == shopAdapter.products.size -1
                    && !endOfData && !isLoading){
                    addLoading()
                    shopViewModel.getMorePages()
                }
            }
        })
    }
    private fun listenToProductsActions(){
        shopAdapter.productOpenLiveData.observe(this, Observer {
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("product", it)
            startActivity(intent)
        })

        shopAdapter.cartClicked.observe(this, Observer {
            if(it != null) {
                showProgress()
                productViewModel.addToCart(it.id, it.index)
            }
        })

        shopAdapter.wishListClicked.observe(this, Observer {
            if(it != null) {
                showProgress()
                productViewModel.addWish(it.id, it.index)
            }
        })

        shopAdapter.compareListClicked.observe(this, Observer {
            if(it != null) {
                showProgress()
                productViewModel.addToCompareList(it)
            }
        })
    }
    private fun reloadData(){
        shopAdapter.products.clear()
        shopAdapter.notifyDataSetChanged()
        addLoading()
        shopViewModel.getShopProducts(minPrice, maxPrice, query, brands?.toList(), orderBy, asc, category)
    }

}
