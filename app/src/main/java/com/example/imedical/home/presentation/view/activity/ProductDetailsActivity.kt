package com.example.imedical.home.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.imedical.R
import com.example.imedical.core.model.ProductModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import javax.inject.Inject

class ProductDetailsActivity : BaseActivity() {

    private var productModel: ProductModel? = null
    private var price: Double = 0.0
    private var quantity: Int = 1

    @Inject lateinit var productViewModelFactory: ViewModelFactory<ProductViewModel>
    private val productViewModel by lazy {
        ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_product_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        productModel = intent.getParcelableExtra("product")
        bindData()
        observeViewModel()
        setActions()
    }

    private fun bindData(){
        if(productModel == null){
            finish()
            showMessage("Something went wrong!")
            return
        }
        Picasso.with(this)
            .load(productModel?.imageUrl)
            .into(ivProductCover)

        tvProductName.text = productModel?.name
        tvProductBrandName.text = if(productModel?.brand != null)
            productModel?.brand
        else "Unknown"
        tvProductDescription.text = productModel?.description
        price = (productModel?.salePrice ?: productModel?.price?: 0.0)
        tvProductPrice.text = price.toString()
        itemProductQuantity.text = quantity.toString()

    }

    private fun observeViewModel(){
        productViewModel.getWishLiveData().observe(this, Observer {
            hideProgress()
            if(it?.status == true) {
                if (productModel?.inWishList == true)
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
            if(it?.status == true) {
                hideProgress()
                showMessage("Added to cart successfully")
            } else showMessage(it?.error)
        })
    }
    private fun setActions(){
        itemCartIncrease.setOnClickListener {
            quantity++
            tvProductQuantity.text = quantity.toString()
            tvProductPrice.text = (quantity * price).toString() + " LE"
        }

        itemCartDecrease.setOnClickListener {
            if(quantity > 1){
                quantity--
                tvProductQuantity.text = quantity.toString()
                tvProductPrice.text = (quantity * price).toString() + " LE"
            }
        }
        ivProductWishList.setOnClickListener {
            if(productModel != null) {
                showProgress()
                if (productModel?.inWishList == true)
                    productViewModel.removeWish(productModel!!.id, 0)
                else productViewModel.addWish(productModel!!.id, 0)
                productModel?.inWishList = !(productModel?.inWishList?:true)
            }
        }

        ivProductCompare.setOnClickListener {
            if(productModel != null) {
                showProgress()
                productViewModel.addToCompareList(productModel!!)
            }
        }
        btnAddToCart.setOnClickListener {
            if(productModel != null) {
                showProgress()
                productViewModel.addToCart(productModel!!.id, quantity)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }
}

