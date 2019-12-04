package com.example.imedical.compare.presentation.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.compare.domain.model.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_compare_product.view.*

class CompareListAdapter(private val compareList: ArrayList<ProductModel>,
                         private val context: Context): RecyclerView.Adapter<CompareListAdapter.CompareListHolder>() {

    val onRemoveClick by lazy { MutableLiveData<ProductModel>() }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CompareListHolder {
        return CompareListHolder(
            LayoutInflater.from(context).inflate(R.layout.cell_compare_product, viewGroup, false), context)

    }

    override fun getItemCount() = compareList.size

    override fun onBindViewHolder(compareListHolder: CompareListHolder, position: Int) {
        compareListHolder.bind(compareList[position], position)
    }

    inner class CompareListHolder(val view: View, val context: Context)
        : RecyclerView.ViewHolder(view){


        fun bind(productModel: ProductModel, index: Int){

            view.tvCompareProductName.text = productModel.name
            if(productModel.salePrice != null) {
                view.tvCompareProductPrice.text = productModel.salePrice.toString()
                view.tvCompareOldPrice.text = productModel.price.toString()
            }
            else {
                view.tvCompareProductPrice.text = productModel.price.toString()
                view.lyCompareOldPriceLayout.visibility = View.GONE
            }

            if(!productModel.brand.isNullOrEmpty()) {
                view.tvCompareProductBrand.visibility = View.VISIBLE
                view.tvCompareProductBrand.text = productModel.brand
            } else {
                view.tvCompareProductBrand.visibility = View.GONE
            }

            Picasso.with(context)
                .load(productModel.imageUrl)
                .into(view.imgCompareProductsImage)

            view.btnCompareRemove.setOnClickListener{ onRemoveClick.value = productModel }
        }
    }
}