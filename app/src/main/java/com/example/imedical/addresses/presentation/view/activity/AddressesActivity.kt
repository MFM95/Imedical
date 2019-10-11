package com.example.imedical.addresses.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.presentation.view.adapter.AddressesAdapter
import com.example.imedical.addresses.presentation.view.dialog.AddAddressDialog
import com.example.imedical.addresses.presentation.view.dialog.AddAddressDialogListener
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.activity_addresses.*
import javax.inject.Inject

class AddressesActivity : BaseActivity(), AddAddressDialogListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddressesViewModel>
    private lateinit var addressesViewModel: AddressesViewModel

    private lateinit var addressesAdapter: AddressesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_addresses)
        addressesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java)
        getAddressesList()
        fabAddressesAdd.setOnClickListener {
            AddAddressDialog().showMaterialDialog(this)
        }
    }

    private fun getAddressesList() {
        addressesViewModel.getAddresses()
        addressesViewModel.getAddressesLiveData().observe(this,
            Observer {
                it?.let {
                    setUpRecyclerView(ArrayList(it))
                }
            })
    }


    private fun setUpRecyclerView(addressesList: ArrayList<AddressModel>) {
        addressesAdapter = AddressesAdapter(addressesList, this)
        rvAddresses.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvAddresses.adapter = addressesAdapter
    }

    override fun onSubmit(
        alias: String,
        address1: String,
        address2: String,
        country: String,
        zip: String,
        phone: String
    ) {
        addressesViewModel.createAddress(alias = alias, address1 = address1, address2 = address2,
            phone = phone, countryId = country, provinceId = "")
        observeOnCreateAddress()
    }

    private fun observeOnCreateAddress() {
        addressesViewModel.getCreatedAddressLiveData().observe(this, Observer {
            it?.let {
                showMessage(getString(R.string.add_addresses_success_msg))
            }
        })
    }


}
