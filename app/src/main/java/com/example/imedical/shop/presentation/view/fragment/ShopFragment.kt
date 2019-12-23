package com.example.imedical.shop.presentation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.ObbInfo
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import com.example.imedical.R

import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.activity.ProductDetailsActivity
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import com.example.imedical.shop.presentation.view.adapter.ShopAdapter
import com.example.imedical.shop.presentation.viewmodel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.*
import javax.inject.Inject
import com.example.imedical.shop.presentation.view.activity.FilterShopActivity
import kotlin.math.max


/**
 * A simple [Fragment] subclass.
 */
class ShopFragment : BaseFragment() {

    private val FILTER_REQUEST_CODE = 1
    @Inject lateinit var viewModelFactory: ViewModelFactory<ShopViewModel>
    private val shopViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(ShopViewModel::class.java) }
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
        subscribeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        listenToProductsActions()
        setClickListeners()
        addLoading()
        shopViewModel.getShopProducts(minPrice, maxPrice, query, brands, orderBy, asc, category)
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
        gridLayoutManager = GridLayoutManager(context, spans)
        rvShop.layoutManager = gridLayoutManager
        shopAdapter = ShopAdapter(arrayListOf(), context!!)
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
            val intent = Intent(activity, ProductDetailsActivity::class.java)
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
    private fun setClickListeners(){
        llShopSort.setOnClickListener {
            openSort()
        }

        llShopFilter.setOnClickListener {
            val intent = Intent(activity, FilterShopActivity::class.java)
            intent.putExtra("brands", brands?.toIntArray())
            startActivityForResult(intent, FILTER_REQUEST_CODE)
            activity?.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        }
        ivItemShopArrange.setOnClickListener {
            //TODO Arrange
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == FILTER_REQUEST_CODE && data != null) {
            if(data.getIntegerArrayListExtra("brands") != null) {
                if(brands == null)
                    brands = ArrayList()
                brands?.addAll(data.getIntegerArrayListExtra("brands"))
            } else brands = null
            minPrice = data.getDoubleExtra("min_price", 0.0)
            maxPrice = data.getDoubleExtra("max_price", 0.0)
            if(maxPrice == 0.0)
                maxPrice = null
            reloadData()
        }
    }

    private fun reloadData(){
        shopAdapter.products.clear()
        shopAdapter.notifyDataSetChanged()
        addLoading()
        shopViewModel.getShopProducts(minPrice, maxPrice, query, brands?.toList(), orderBy, asc, category)

    }
    private fun openSort(){
        val dialog = BottomSheetDialog(activity!!, R.style.CustomDialogTheme)
        dialog.setContentView(R.layout.bottom_sheet_dialog_shop_sort)
        dialog.show()
        dialog.findViewById<Button>(R.id.btnSort)?.setOnClickListener {
            val selected = dialog.findViewById<RadioGroup>(R.id.rgSort)?.checkedRadioButtonId
            when(selected){
                R.id.newestSort ->{
                    orderBy = "created_at"
                    asc = true
                }
                R.id.oldestSort ->{
                    orderBy = "created_at"
                    asc = null
                }
                R.id.cheapestSort ->{
                    orderBy = "price"
                    asc = true
                }
                R.id.expensiveSort -> {
                    orderBy = "price"
                    asc = null
                }
            }
            dialog.dismiss()
            reloadData()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            ShopFragment()
    }
}
