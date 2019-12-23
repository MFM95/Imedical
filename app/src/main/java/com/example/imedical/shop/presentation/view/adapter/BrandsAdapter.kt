package com.example.imedical.shop.presentation.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.home.data.entity.BrandEntity
import kotlinx.android.synthetic.main.item_brand_filter.view.*

class BrandsAdapter (val brands: ArrayList<BrandEntity?>,val context: Context): RecyclerView.Adapter<BrandsAdapter.BrandHolder>() {

    val brandClickedLiveData by lazy { MutableLiveData<BrandEntity>() }
    override fun getItemCount(): Int  = brands.size

    override fun onBindViewHolder(holder: BrandHolder, index: Int) {
        holder.bind(brands[index])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder {
        return BrandHolder( context,
                LayoutInflater.from(context).inflate(R.layout.item_brand_filter, parent, false)
            )
    }

    inner class BrandHolder(val context: Context, val view: View): RecyclerView.ViewHolder(view){
        fun bind(model: BrandEntity?){
            view.tvBrandItemName.text = model?.name
            if(model?.selected == true)
                setSelected()

            view.tvBrandItemName.setOnClickListener {
                brandClickedLiveData.value = model
                if(model?.selected == true) {
                    setUnselected()
                    model.selected = false
                }
                else {
                    setSelected()
                    model?.selected = true
                }
            }
        }

        private fun setSelected(){
            view.tvBrandItemName.setBackgroundResource(R.drawable.bg_rounded_edges_faded_blue)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.tvBrandItemName.setTextColor(context.getColor(R.color.colorWhite))
            } else view.tvBrandItemName.setTextColor(context.resources.getColor(R.color.colorWhite))
        }

        private fun setUnselected(){
            view.tvBrandItemName.setBackgroundResource(R.drawable.rect_blue_stroke)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.tvBrandItemName.setTextColor(context.getColor(R.color.faded_blue))
            } else view.tvBrandItemName.setTextColor(context.resources.getColor(R.color.faded_blue))
        }
    }
}