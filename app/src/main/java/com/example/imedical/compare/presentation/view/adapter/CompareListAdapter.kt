package com.example.imedical.compare.presentation.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.imedical.R
import com.example.imedical.compare.domain.model.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_compare_product.view.*

class CompareListAdapter(private val compareList: ArrayList<ProductModel>,
                         private val compareListCallback: ICompareListCallback,
                         private val context: Context): BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.cell_compare_product, null)
        val item = compareList[position]
        view.tvCompareProductName.text = item.name
        if(item.salePrice != null) {
            view.tvCompareProductPrice.text = item.salePrice.toString()
            view.tvCompareOldPrice.text = item.price.toString()
        }
        else {
            view.tvCompareProductPrice.text = item.price.toString()
            view.tvCompareOldPrice.visibility = View.GONE
        }

        Picasso.with(context)
            .load(item.imageUrl)
            .into(view.imgCompareProductsImage)

        view.btnCompareProductWish.setOnClickListener {
          compareListCallback.onWishClick(item.id)
        }

        view.btnCompareProductCart.setOnClickListener {
            compareListCallback.addToCart(item.id)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return compareList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return compareList.size
    }
}