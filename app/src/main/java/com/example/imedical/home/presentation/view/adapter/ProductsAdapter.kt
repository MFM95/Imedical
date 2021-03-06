package com.example.imedical.home.presentation.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.PorterDuff
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.imedical.R
import com.example.imedical.core.model.ProductModel
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class ProductsAdapter(val products: ArrayList<ProductModel>,
                      private val productCallback: IProductCallback,
                      private val context: Context): RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    val onCompareClick by lazy { MutableLiveData<ProductModel>() }
    val onWishClick by lazy { MutableLiveData<Int>() }
    val onAddToCartClick by lazy { MutableLiveData<Int>() }
    val onProductItemClick by lazy { MutableLiveData<ProductModel>() }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cell_product,
            viewGroup, false), productCallback, context)

    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(productHolder: ProductHolder, position: Int) {
        productHolder.bind(products[position], position)
    }

    inner class ProductHolder(val view: View, private val productCallback: IProductCallback, val context: Context)
        : RecyclerView.ViewHolder(view){

        lateinit var productImage: ImageView
        lateinit var productName: TextView
        lateinit var productPrice: TextView
        lateinit var productOldPrice: TextView

        lateinit var wishButton: ImageButton
        lateinit var compareButton: ImageView
        lateinit var addCartButton: Button

        lateinit var oldPriceLayout: ConstraintLayout

        fun bind(productModel: ProductModel, index: Int){
            productImage = view.findViewById(R.id.productImageView)
            productName = view.findViewById(R.id.productNameTextView)
            productPrice = view.findViewById(R.id.productPriceTextView)
            productOldPrice = view.findViewById(R.id.productOldPriceTextView)

            wishButton = view.findViewById(R.id.productWishButton)
            compareButton = view.findViewById(R.id.productCompareButton)
            addCartButton = view.findViewById(R.id.productCartButton)

            oldPriceLayout = view.findViewById(R.id.oldPriceConstraintLayout)

            productName.text = productModel.name
            if(productModel.salePrice != null) {
                productPrice.text = productModel.salePrice.toString()
                productOldPrice.text = productModel.price.toString()
            }
            else {
                productPrice.text = productModel.price.toString()
                oldPriceLayout.visibility = View.GONE
            }

            Picasso.with(context)
                .load(productModel.imageUrl)
                .into(productImage)

            if(productModel.inWishList) {
                wishButton.setImageDrawable(context.getDrawable(R.drawable.ic_wish_full))
                wishButton.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent))
            }
            else {
                wishButton.setImageDrawable(context.getDrawable(R.drawable.ic_wish_border_sm))
                wishButton.setColorFilter(ContextCompat.getColor(context, R.color.colorBlack))

            }

            wishButton.setOnClickListener{ productCallback.onWishClick(productModel.id, index) }
            compareButton.setOnClickListener{ productCallback.onCompareClick(productModel) }
            addCartButton.setOnClickListener{ productCallback.addToCart(productModel.id) }

        }
    }
}