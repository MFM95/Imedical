package com.example.imedical.shop.presentation.view.adapter

import android.animation.Animator
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.core.model.ProductModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopAdapter(val products: ArrayList<ProductModel?>,val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADIMG = 1

    val wishListClicked by lazy { MutableLiveData<ProductModel>() }
    val compareListClicked by lazy { MutableLiveData<ProductModel>() }
    val cartClicked by lazy { MutableLiveData<ProductModel>() }
    val productOpenLiveData by lazy { MutableLiveData<ProductModel>() }
    override fun getItemCount(): Int  = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
        if(holder is ProductHolder)
            holder.bind(products[index], index)
        else if(holder is LoadingViewHolder)
            holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM)
            ProductHolder( context,
                LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false)
            )
        else LoadingViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if(products[position] == null) VIEW_TYPE_LOADIMG else VIEW_TYPE_ITEM
    }
    inner class ProductHolder(val context: Context, val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: ProductModel?, index: Int){
            if(model != null) {
                view.tvItemShopName.text = model.name
                if (model.salePrice != null) {
                    view.tvItemShopPrice.text = model.salePrice.toString()
                    val dif = model.price.toFloat() - model.salePrice.toFloat()
                    val percntage = (dif / model.price.toFloat()) * 100.0f
                    view.tvItemShopOffer.text = "Offer   " + percntage.toInt() + "%"
                } else {
                    view.tvItemShopPrice.text = model.price.toString()
                    view.tvItemShopOffer.visibility = View.GONE
                }

                Picasso.with(context)
                    .load(model.imageUrl)
                    .transform(RoundedCornersTransformation(30, 2))
                    .into(view.ivItemShopImage)

                view.ivItemShopCompareList.setOnClickListener {
                    model.index = index
                    compareListClicked.value = model
                }
                view.ivItemShopWishList.setOnClickListener {
                    model.index = index
                    model.inCompareList = !model.inCompareList
                    wishListClicked.value = model
                }
                view.ivItemShopCart.setOnClickListener {
                    model.index = index
                    cartClicked.value = model
                }
                view.ivItemShopImage.setOnClickListener {
                    model.index = index
                    productOpenLiveData.value = model
                }
                view.clItemShopLayout.setOnClickListener {
                    model.index = index
                    productOpenLiveData.value = model
                }
            }
        }
    }

    inner class LoadingViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(){
            animateSecond()
        }

        private fun animateSecond(){
            val animator = view.ivLoading2.animate().alpha(1.0f)
            animator.duration = 600
            animator.start()
            animator.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    animateThird()
                }
            })
        }
        private fun animateThird(){
            val animator = view.ivLoading3.animate().alpha(1.0f)
            animator.duration = 600
            animator.start()
            animator.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    animateFourth()
                }
            })
        }
        private fun animateFourth(){
            val animator = view.ivLoading4.animate().alpha(1.0f)
            animator.duration = 600
            animator.start()
            animator.setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                }
                override fun onAnimationCancel(animation: Animator?) {
                }
                override fun onAnimationRepeat(animation: Animator?) {
                }
                override fun onAnimationEnd(animation: Animator?) {
                    view.ivLoading2.alpha = 0.0f
                    view.ivLoading3.alpha = 0.0f
                    view.ivLoading4.alpha = 0.0f
                    animateSecond()
                }
            })
        }
    }
}