package com.example.imedical.addresses.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_edit_address.*
import javax.inject.Inject


class EditAddressFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var addressModel: AddressModel? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddressesViewModel>
    private lateinit var addressesViewModel: AddressesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        arguments?.let {

        }
        addressesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        addressModel?.let {
            edtUpdateAddressAlias.setText(it.alias?: "")
            edtUpdateAddressAddress1.setText(it.address_1?: "")
            edtUpdateAddressAddress2.setText(it.address_2?: "")
            it.country?.let { country ->
                edtUpdateAddressCountry.setText(country.name?: "")
            }
            edtUpdateAddressZip.setText("")
            edtUpdateAddressPhone.setText(it.phone)
        }

        btnUpdateAddress.setOnClickListener {
            if (isValidData())
                updateAddress()
        }
    }

    private fun updateAddress() {
        val alias = edtUpdateAddressAlias.text.toString()
        val address1 = edtUpdateAddressAddress1.text.toString()
        val address2 = edtUpdateAddressAddress2.text.toString()
        val country = edtUpdateAddressCountry.text.toString()
        val zip = edtUpdateAddressZip.text.toString()
        val phone = edtUpdateAddressPhone.text.toString()
        val id = addressModel?.id?: "0"
        addressesViewModel.updateAddress(id = id, alias = alias, address1 = address1, address2 = address2,
            phone = phone, countryId = country, provinceId = zip)

        addressesViewModel.getUpdateAddressLiveData().observe(this, Observer {
            if(it != null) {
                showMessage(getString(R.string.update_addresses_success_msg))
            } else {
                showMessage(getString(R.string.update_addresses_failure_msg))
            }
        })
    }

    private fun isValidData(): Boolean {
        if(edtUpdateAddressAlias!!.text!!.isEmpty()) {
            edtUpdateAddressAlias!!.error = context!!.getString(R.string.add_addresses_alias_empty)
            //       showMessage(getString(R.string.add_addresses_alias_empty))
            return false
        }

        if(edtUpdateAddressAddress1!!.text!!.isEmpty()) {
            edtUpdateAddressAddress1!!.error = context!!.getString(R.string.add_addresses_address_empty)
            //   showMessage(getString(R.string.add_addresses_address_empty))
            return false
        }


        if(edtUpdateAddressPhone!!.text!!.isEmpty()) {
            edtUpdateAddressPhone!!.error = context!!.getString(R.string.add_addresses_phone_empty)
            //   showMessage(getString(R.string.add_addresses_phone_empty))
            return false
        }

        return true
    }

    companion object {

        private const val EXTRA_ADDRESS_MODEL = "extra_address_model"
       @JvmStatic
        fun newInstance(addressModel: AddressModel) =
            EditAddressFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ADDRESS_MODEL, addressModel)
                }
            }
    }
}
