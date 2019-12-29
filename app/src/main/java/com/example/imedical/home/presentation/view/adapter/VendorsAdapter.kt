package com.example.imedical.home.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.home.data.entity.VendorEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_vendor.view.*

class VendorsAdapter (val vendors: ArrayList<VendorEntity>, private val context: Context) : RecyclerView.Adapter<VendorsAdapter.VendorHolder>(){
    override fun getItemCount(): Int {
        return vendors.size
    }

    override fun onBindViewHolder(holder: VendorHolder, position: Int) {
        holder.bind(vendors[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): VendorHolder {
        return VendorHolder(
            LayoutInflater.from(context).inflate(R.layout.item_vendor, parent, false)
        )
    }

    inner class VendorHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(vendor: VendorEntity){
            view.vendorName.text = vendor.name
            Picasso.with(context).load(vendor.image).into(view.vendorImage)
        }
    }
}