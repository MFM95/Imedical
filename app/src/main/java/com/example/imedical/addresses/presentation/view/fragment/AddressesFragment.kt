package com.example.imedical.addresses.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ScrollView

import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.domain.model.Country
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

    private var provincesIds = ArrayList<Int>()
    private var provincesNames = ArrayList<String>()
    private var provincesList: List<Province> = ArrayList<Province>()
    private var addresses = ArrayList<AddressModel>()
    private var selectedProvinceId = 0
    private var selectedProvinceName = ""
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
                    if(!provincesList.isNullOrEmpty())
                        setUpRecyclerView()
                }
            })
    }

    private fun initUI () {
        tvAddAddress.setOnClickListener {
            if(provincesIds.isNullOrEmpty()) {
                getProvinces(true)
            } else {
                tvAddAddress.visibility = View.GONE
                lyAddAddressLayout.visibility = View.VISIBLE
                scrollAddresses.fullScroll(ScrollView.FOCUS_DOWN)
                edtAddressStreet.setText("")
                edtAddressPhone.setText("")
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
        val provinceId = selectedProvinceId
        val provinceName = selectedProvinceName
        addressesViewModel.createAddress(alias = alias, address1 = street, address2 = "",
            phone = phone, countryId = 1, provinceId = provinceId)
        if(!addressesViewModel.getCreatedAddressLiveData().hasObservers())
        addressesViewModel.getCreatedAddressLiveData().observe(this, Observer {
            if(it != null) {
                showMessage(getString(R.string.add_addresses_success_msg))
                tvAddAddress.visibility = View.VISIBLE
                lyAddAddressLayout.visibility = View.GONE
                val address = AddressModel(
                    alias = alias, address_1 = street, address_2 = "",
                    phone = phone,
                    country = Country(id = 1, name = ""),
                    province = Province(id = provinceId, name = provinceName))
                addresses.add(address)
                setUpRecyclerView()
//                addressesAdapter.apply {
//                    notifyItemChanged(itemCount-1)
//                  }
            } else {
                showMessage(getString(R.string.add_addresses_failure_msg))
            }
        })
    }

    private fun getProvinces(showAddAddress: Boolean) {
        addressesViewModel.getCountryProvinces(1)
        if(!addressesViewModel.getCountryProvincesLiveData().hasObservers())
        addressesViewModel.getCountryProvincesLiveData().observe(this, Observer {
            it?.let {
                provincesList = it.provinces
                for (province in provincesList)  {
                    provincesNames.add(province.name!!)
                    provincesIds.add(province.id!!)
                }
                setUpSpinner(provincesNames)
                selectedProvinceId = provincesIds[0]
                selectedProvinceName = provincesNames[0]
                if(showAddAddress)
                    showAddAddressLayout()
                else {
                    if(!addresses.isNullOrEmpty())
                        setUpRecyclerView()

                }
            }
        })
    }

    private fun showAddAddressLayout() {
        tvAddAddress.visibility = View.GONE
        lyAddAddressLayout.visibility = View.VISIBLE
        scrollAddresses.fullScroll(ScrollView.FOCUS_DOWN)
        edtAddressStreet.setText("")
        edtAddressPhone.setText("")
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
                selectedProvinceId = provincesIds[position]
            }

        }
    }
    private fun setUpRecyclerView() {
        addressesAdapter = AddressesAdapter(addresses, activity!!, provincesList)
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
        if(addressesViewModel.getDeleteAddressLiveData().hasObservers())
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
                editAddress(it.first, it.second)
            }
        })
    }

    private fun editAddress(address: AddressModel, position: Int) {
        addressesViewModel.updateAddress(address.id?: 0, address.alias, address.address_1, address.address_2, address.country!!.id, address.province?.id, address.phone)
        addressesViewModel.getUpdateAddressLiveData().observe(this, Observer {
            showMessage(it)
            if(addresses.size > position) {
                addresses[position] = address
                setUpRecyclerView()
            }

        })
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            AddressesFragment().apply {
            }
    }
}
