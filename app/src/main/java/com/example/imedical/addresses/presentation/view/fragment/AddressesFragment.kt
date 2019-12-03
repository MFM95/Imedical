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

import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        addressesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java)
        getAddressesList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addresses, container, false)
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
        addressesAdapter = AddressesAdapter(addressesList, activity!!)
        rvAddresses.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvAddresses.adapter = addressesAdapter
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            AddressesFragment().apply {
            }
    }
}
