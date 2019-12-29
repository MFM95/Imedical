package com.example.imedical.home.presentation.view.activity

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.imedical.R
import com.example.imedical.addresses.domain.model.AddressModel
import com.example.imedical.addresses.presentation.view.activity.ProfileActivity
import com.example.imedical.addresses.presentation.viewmodel.AddressesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.adapter.ChooseAddressAdapter
import com.example.imedical.orders.presentation.activity.OrdersActivity
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
                startActivity(Intent(this, OrdersActivity::class.java))
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
            dialog = Dialog(this, android.R.style.Theme)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.dialog_choose_address)
        }
        if(chooseAddressAdapter.data.size == 0){
            AlertDialog.Builder(this)
                .setTitle("No Addresses")
                .setMessage("Go to profile to add Address?")
                .setPositiveButton("Add Address") { dialog, which ->
                    startActivityForResult(Intent(this, ProfileActivity::class.java), 100)
                }
                .setNegativeButton("Cancel") { dialog, which ->  dialog.dismiss()}
                .create().show()
            return
        }
        if(dialog?.isShowing == false)
            dialog?.show()
        val rv = dialog?.findViewById<RecyclerView>(R.id.addressRecyclerView)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv?.adapter = chooseAddressAdapter
        dialog?.findViewById<ImageView>(R.id.closeBtn)?.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.findViewById<TextView>(R.id.newAddress)?.setOnClickListener {
            startActivityForResult(Intent(this, ProfileActivity::class.java), 100)
        }
    }

    private val  chooseAddressCallback = object : ChooseAddressAdapter.AddressCallback {
        override fun onClicked(addressModel: AddressModel) {
            dialog?.dismiss()
            addressDetails.text = addressModel.address_1
            address = addressModel
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100){
            addressesViewModel.getAddresses()
            address = null
            addressDetails.text = ""
        }
    }
}
