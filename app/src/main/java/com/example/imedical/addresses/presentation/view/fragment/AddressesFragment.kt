package com.example.imedical.addresses.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ScrollView

import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.Province
import com.example.imedical.addresses.presentation.view.adapter.AddressesAdapter
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_addresses.*
import javax.inject.Inject


class AddressesFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddressesViewModel>
    private lateinit var addressesViewModel: AddressesViewModel

    private lateinit var addressesAdapter: AddressesAdapter

    private var provinces = ArrayList<Int>()
    private var provincesList: List<Province> = ArrayList<Province>()
    private var addresses = ArrayList<AddressModel>()
    private var selectedProvince = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        addressesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addresses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        getProvinces(false)
        getAddressesList()

    }

    private fun getAddressesList() {
        addressesViewModel.getAddresses()
        addressesViewModel.getAddressesLiveData().observe(this,
            Observer {
                it?.let {
                    addresses = ArrayList(it)
                    setUpRecyclerView(addresses)
                }
            })
    }

    private fun initUI () {
        tvAddAddress.setOnClickListener {
            if(provinces.isNullOrEmpty()) {
                getProvinces(true)
            } else {
                tvAddAddress.visibility = View.GONE
                lyAddAddressLayout.visibility = View.VISIBLE
                scrollAddresses.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }

        btnAddressAdd.setOnClickListener {
            addAddress()
        }
    }

    private fun addAddress() {
        val alias = "Home"
        val street = edtAddressStreet.text.toString()
        val phone = edtAddressPhone.text.toString()
        addressesViewModel.createAddress(alias = alias, address1 = street, address2 = "",
            phone = phone, countryId = 1, provinceId = selectedProvince)
        addressesViewModel.getCreatedAddressLiveData().observe(this, Observer {
            if(it != null) {
                showMessage(getString(R.string.add_addresses_success_msg))
                tvAddAddress.visibility = View.VISIBLE
                lyAddAddressLayout.visibility = View.GONE
                // todo add address to the list
                addressesAdapter.apply {
                    notifyDataSetChanged()
                }
            } else {
                showMessage(getString(R.string.add_addresses_failure_msg))
            }
        })
    }

    private fun getProvinces(showAddAddress: Boolean) {
        addressesViewModel.getCountryProvinces(1)
        addressesViewModel.getCountryProvincesLiveData().observe(this, Observer {
            it?.let {
                provincesList = it.provinces
                val provincesData = ArrayList<String>()
                for (province in provincesList)  {
                    provincesData.add(province.name!!)
                    provinces.add(province.id!!)
                }
                setUpSpinner(provincesData)
                selectedProvince = provinces[0]
                if(showAddAddress)
                    showAddAddressLayout()
            }
        })
    }

    private fun showAddAddressLayout() {
        tvAddAddress.visibility = View.GONE
        lyAddAddressLayout.visibility = View.VISIBLE
        scrollAddresses.fullScroll(ScrollView.FOCUS_DOWN)
    }

    private fun setUpSpinner(items: ArrayList<String>) {
        val arrayAdapter = ArrayAdapter<String>(activity!!, R.layout.support_simple_spinner_dropdown_item, items)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinnerAddressCity.adapter = arrayAdapter
        spinnerAddressCity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProvince = provinces[position]
            }

        }
    }
    private fun setUpRecyclerView(addressesList: ArrayList<AddressModel>) {
        addressesAdapter = AddressesAdapter(addressesList, activity!!, provincesList)
        rvAddresses.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvAddresses.adapter = addressesAdapter
        observeOnDeleteItem()
        observeOnEditItem()
    }

    private fun observeOnDeleteItem() {
        addressesAdapter.onDeleteClickLiveData.observe(this, Observer {
            it?.let {
                deleteAddress(it)
            }
        })
    }

    private fun deleteAddress(address: AddressModel) {
        addressesViewModel.deleteAddress(address.id!!)
        addressesViewModel.getDeleteAddressLiveData().observe(this, Observer {
            it?.let {
                showMessage(it)
                if (addresses.contains(address))
                    addresses.remove(address)
                addressesAdapter?.apply {
                    notifyDataSetChanged()
                }
            }
        })
    }

    private fun observeOnEditItem() {
        addressesAdapter.onEditClickLiveData.observe(this, Observer {
            it?.let {
                editAddress(it!!)
            }
        })
    }

    private fun editAddress(address: AddressModel) {
        addressesViewModel.updateAddress(address.id?: 0, address.alias, address.address_1, address.address_2, address.country!!.id, address.province?.id, address.phone)
        addressesViewModel.getUpdateAddressLiveData().observe(this, Observer {
            showMessage(it)
        })
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            AddressesFragment().apply {
            }
    }
}
