package com.example.imedical.addresses.presentation.view.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.dialog_add_address.*
import javax.inject.Inject


class AddAddressFragment : BaseFragment() {

    private var fab: FloatingActionButton? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddressesViewModel>
    private lateinit var addressesViewModel: AddressesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        addressesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = activity!!.findViewById(R.id.fabAddressesAdd)
        btnAddAddress.setOnClickListener {
            if (isValidData())
                addAddress()
        }
    }

    private fun addAddress() {
        val alias = edtAddAddressAlias.text.toString()
        val address1 = edtAddAddressAddress1.text.toString()
        val address2 = edtAddAddressAddress2.text.toString()
        val country = edtAddAddressCountry.text.toString()
        val zip = edtAddAddressZip.text.toString()
        val phone = edtAddAddressPhone.text.toString()
        addressesViewModel.createAddress(alias = alias, address1 = address1, address2 = address2,
            phone = phone, countryId = country, provinceId = zip)

        addressesViewModel.getCreatedAddressLiveData().observe(this, Observer {
            if(it != null) {
                showMessage(getString(R.string.add_addresses_success_msg))
            } else {
                showMessage(getString(R.string.add_addresses_failure_msg))
            }
        })
    }

    private fun isValidData(): Boolean {
        if(edtAddAddressAlias!!.text!!.isEmpty()) {
            edtAddAddressAlias!!.error = context!!.getString(R.string.add_addresses_alias_empty)
     //       showMessage(getString(R.string.add_addresses_alias_empty))
            return false
        }

        if(edtAddAddressAddress1!!.text!!.isEmpty()) {
            edtAddAddressAddress1!!.error = context!!.getString(R.string.add_addresses_address_empty)
         //   showMessage(getString(R.string.add_addresses_address_empty))
            return false
        }


        if(edtAddAddressPhone!!.text!!.isEmpty()) {
            edtAddAddressPhone!!.error = context!!.getString(R.string.add_addresses_phone_empty)
         //   showMessage(getString(R.string.add_addresses_phone_empty))
            return false
        }

        return true
    }

    @SuppressLint("RestrictedApi")
    override fun onResume() {
        super.onResume()
        fab?.let {
            it.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onPause() {
        super.onPause()
        fab?.let {
            it.visibility = View.VISIBLE
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() =
            AddAddressFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
