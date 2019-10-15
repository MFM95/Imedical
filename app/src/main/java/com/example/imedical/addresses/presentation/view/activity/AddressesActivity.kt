package com.example.imedical.addresses.presentation.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.presentation.view.adapter.AddressesAdapter
import com.example.imedical.addresses.presentation.view.fragment.AddAddressFragment
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.activity_addresses.*
import javax.inject.Inject


class AddressesActivity : BaseActivity() {

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
            showAddAddressFragment()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun showAddAddressFragment() {
        val fragment = AddAddressFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.lyAddFragmenContainer, fragment)
            .addToBackStack("")
            .commit()
    }

    @SuppressLint("RestrictedApi")
    override fun onResume() {
        super.onResume()
        getAddressesList()

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




}
