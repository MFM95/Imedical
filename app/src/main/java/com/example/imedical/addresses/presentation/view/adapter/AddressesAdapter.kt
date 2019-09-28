package com.example.imedical.addresses.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import kotlinx.android.synthetic.main.cell_address.view.*

class AddressesAdapter(
    private val addressesList: ArrayList<AddressModel>,
    private val context: Context
) : RecyclerView.Adapter<AddressesAdapter.AddressesListHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AddressesListHolder {
        return AddressesListHolder(
            LayoutInflater.from(context).inflate(R.layout.cell_address, viewGroup, false),
            context
        )
    }

    override fun getItemCount(): Int {
        return addressesList.size
    }

    override fun onBindViewHolder(addressesListHolder: AddressesListHolder, position: Int) {
        addressesListHolder.bind(addressesList[position], position)
    }

    inner class AddressesListHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {

        fun bind(addressModel: AddressModel, position: Int) {
            view.tvAddressItemAlias.text = addressModel.alias
            view.tvAddressItemAddress1.text = addressModel.address_1
            view.tvAddressItemAddress2.text = addressModel.address_2
            view.tvAddressItemCountry.text = addressModel.country?.name?: ""
            view.tvAddressItemCity.text = addressModel.province?.name?: ""
            view.tvAddressItemPhone.text = addressModel.phone


        }
    }
}