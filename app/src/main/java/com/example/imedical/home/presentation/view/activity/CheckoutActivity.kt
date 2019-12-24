package com.example.imedical.home.presentation.view.activity

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.adapter.ChooseAddressAdapter
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class CheckoutActivity : BaseActivity() {

    private val chooseAddressAdapter by lazy { ChooseAddressAdapter(ArrayList(), chooseAddressCallback, this) }
    private var dialog: Dialog? = null
    private var address: AddressModel? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AddressesViewModel>
    private val addressesViewModel: AddressesViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(AddressesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_checkout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Checkout"
        supportActionBar?.title = "Checkout"
        observeClickListener()
        getAddressesList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun observeClickListener(){
        addressLayout.setOnClickListener {
            openChooseAddress()
        }

        checkoutBtn.setOnClickListener {
            if(address == null){
                showMessage("Please choose address first!")
            } else {
                showProgress()
                address?.id?.let { addressesViewModel.checkout(it) }
            }
        }
        addressesViewModel.checkoutLiveData.observe(this, Observer {
            hideProgress()
            if(it?.status == true){
                showMessage("Order is placed successfully")
                val intent = Intent()
                intent.putExtra("success", true)
                setResult(1, intent)
                finish()
            } else showMessage(it?.error)
        })
    }

    private fun getAddressesList() {
        addressesViewModel.getAddresses()
        addressesViewModel.getAddressesLiveData().observe(this,
            Observer {
                it?.let {
                    chooseAddressAdapter.data.clear()
                    chooseAddressAdapter.data.addAll(it)
                    chooseAddressAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun openChooseAddress(){
        if(dialog == null) {
            dialog = Dialog(this, R.style.CustomDialogTheme)
            dialog?.setContentView(R.layout.dialog_choose_address)
        }
        if(dialog?.isShowing == false)
            dialog?.show()
        val rv = dialog?.findViewById<RecyclerView>(R.id.addressRecyclerView)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv?.adapter = chooseAddressAdapter
    }

    private val  chooseAddressCallback = object : ChooseAddressAdapter.AddressCallback {
        override fun onClicked(addressModel: AddressModel) {
            dialog?.dismiss()
            addressDetails.text = addressModel.address_1
            address = addressModel
        }
    }
}
