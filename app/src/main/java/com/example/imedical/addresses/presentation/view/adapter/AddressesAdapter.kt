package com.example.imedical.addresses.presentation.view.adapter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.location.Address
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.Province
import kotlinx.android.synthetic.main.fragment_addresses.view.*
import kotlinx.android.synthetic.main.item_address.view.*

class AddressesAdapter(
    private val addressesList: ArrayList<AddressModel>,
    private val context: Context,
    private val provinces: List<Province>
) : RecyclerView.Adapter<AddressesAdapter.AddressesListHolder>() {

    val onEditClickLiveData by lazy { MutableLiveData<AddressModel>() }
    val onDeleteClickLiveData by lazy { MutableLiveData<AddressModel>() }

    private var provincesIds = ArrayList<Int>()
    private var selectedProvince = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AddressesListHolder {
        return AddressesListHolder(
            LayoutInflater.from(context).inflate(R.layout.item_address, viewGroup, false),
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
            view.tvAddressCity.text = addressModel.province?.name
            view.tvAddressStreet.text = addressModel.address_1
            view.tvAddressPhone.text = addressModel.phone

            if(position == addressesList.size-1) {
                view.viewAddressItemDivider.visibility = View.GONE
            } else {
                view.viewAddressItemDivider.visibility = View.VISIBLE
            }

            view.btnAddressDelete.setOnClickListener {
                onDeleteClickLiveData.value = addressModel
            }


            // edit layout

            view.btnAddressEdit.setOnClickListener {
                view.lyAddressLayout.visibility = View.GONE
                view.lyEditAddressLayout.visibility = View.VISIBLE
            }

            view.btnEditAddressSubmit.setOnClickListener {
                if (selectedProvince == 0)
                    selectedProvince = addressModel.province?.id!!
                var updatedAddress = addressModel
                updatedAddress.phone = view.edtEditAddressPhone.text.toString()
                updatedAddress.address_1 = view.edtEditAddressStreet.text.toString()
                updatedAddress.province = Province(selectedProvince, "")
                onEditClickLiveData.value = updatedAddress
                view.lyAddressLayout.visibility = View.VISIBLE
                view.lyEditAddressLayout.visibility = View.GONE
            }

            val provincesNames = ArrayList<String>()
            provinces?.let {
                for (item in provinces) {
                    provincesNames.add(item.name!!)
                    provincesIds.add(item.id!!)
                    if (item.id == addressModel.province!!.id) {
                    }
                }
            }
            setUpSpinner(provincesNames)

            view.edtEditAddressStreet.setText(addressModel.address_1.toString())
            view.edtEditAddressPhone.setText(addressModel.phone.toString())
        }

        private fun setUpSpinner(items: ArrayList<String>) {
            val arrayAdapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, items)
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            view.spinnerEditAddressCity.adapter = arrayAdapter
            view.spinnerEditAddressCity.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedProvince = provincesIds[position]
                }

            }
        }

    }

}