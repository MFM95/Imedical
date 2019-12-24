package com.example.imedical.home.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import kotlinx.android.synthetic.main.item_address_choose.view.*

class ChooseAddressAdapter (val data: ArrayList<AddressModel>,
private val callback: AddressCallback,
private val context: Context): RecyclerView.Adapter<ChooseAddressAdapter.AddressHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AddressHolder {
        return AddressHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_address_choose,
                viewGroup, false), callback, context)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class AddressHolder (val view: View, private val callback: AddressCallback, val context: Context)
        : RecyclerView.ViewHolder(view){
        fun bind(model: AddressModel){
            view.addressName.text = model.country?.name
            view.addressDetails.text = model.address_1 + " " + model.address_2
            view.clAddressCell.setOnClickListener {
                callback.onClicked(model)
            }
        }
    }

    interface AddressCallback{
        fun onClicked(addressModel: AddressModel)
    }
}