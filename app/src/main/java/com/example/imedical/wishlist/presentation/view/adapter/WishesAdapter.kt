package com.example.imedical.wishlist.presentation.view.adapter

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
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
import com.example.imedical.home.presentation.view.activity.ProductDetailsActivity
import com.example.imedical.home.presentation.view.adapter.IProductCallback
import com.example.imedical.wishlist.presentation.view.adapter.callback.IWishCallback
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */
class WishesAdapter(val wishes: ArrayList<ProductModel>,
                    private val wishCallback: IWishCallback,
                    private val context: Context): RecyclerView.Adapter<WishesAdapter.ProductHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cell_wished_product,
            viewGroup, false), wishCallback, context)

    }

    override fun getItemCount() = wishes.size

    override fun onBindViewHolder(productHolder: ProductHolder, position: Int) {
        productHolder.bind(wishes[position], position)
    }

    inner class ProductHolder(val view: View, private val wishCallback: IWishCallback, val context: Context)
        : RecyclerView.ViewHolder(view){

        lateinit var productImage: ImageView
        lateinit var productName: TextView
        lateinit var productPrice: TextView
        lateinit var productOldPrice: TextView

        lateinit var removeButton: ImageButton
        lateinit var compareButton: ImageView
        lateinit var addCartButton: ImageButton

        lateinit var oldPriceLayout: ConstraintLayout

        fun bind(productModel: ProductModel, index: Int){
            productImage = view.findViewById(R.id.productImageView)
            productName = view.findViewById(R.id.productNameTextView)
            productPrice = view.findViewById(R.id.productPriceTextView)
            productOldPrice = view.findViewById(R.id.productOldPriceTextView)

            removeButton = view.findViewById(R.id.productDeleteButton)
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
            productImage.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra("product", productModel)
                context.startActivity(intent)

            }
            removeButton.setOnClickListener{ wishCallback.onRemoveClick(productModel.id, index) }
            compareButton.setOnClickListener{ wishCallback.onCompareClick(productModel.id) }
            addCartButton.setOnClickListener{ wishCallback.addToCart(productModel.id) }

        }
    }
}