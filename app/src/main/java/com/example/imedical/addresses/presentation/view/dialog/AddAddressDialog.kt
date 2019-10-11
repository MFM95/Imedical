package com.example.imedical.addresses.presentation.view.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.imedical.R
import kotlinx.android.synthetic.main.dialog_add_address.view.*

class AddAddressDialog {

    private var dialog: MaterialDialog? = null
    private var context: Context? = null
    private var listener: AddAddressDialogListener? = null


    fun showMaterialDialog(context: Context) {
        this.context = context
        dialog = MaterialDialog(context)
            .customView(R.layout.dialog_add_address, scrollable = true)

        dialog?.view!!.btnAddAddress.setOnClickListener {
            if (isValidData()) {
                val alias = dialog?.view!!.edtAddAddressAlias.text.toString()
                val address1 = dialog?.view!!.edtAddAddressAddress1.text.toString()
                val address2 = dialog?.view!!.edtAddAddressAddress2.text.toString()
                val country = dialog?.view!!.edtAddAddressCountry.text.toString()
                val zip = dialog?.view!!.edtAddAddressZip.text.toString()
                val phone = dialog?.view!!.edtAddAddressPhone.text.toString()
                listener?.onSubmit(alias, address1, address2, country, zip, phone)
            }
        }
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layoutParams = dialog!!.window!!.attributes
//        layoutParams.width = ActionBar.LayoutParams.MATCH_PARENT
//        layoutParams.height = ActionBar.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = layoutParams
        dialog!!.show()
    }

    private fun isValidData(): Boolean {
        if(dialog?.view!!.edtAddAddressAlias!!.text!!.isEmpty()) {
            dialog?.view!!.edtAddAddressAlias!!.error = context!!.getString(R.string.add_addresses_alias_empty)
            return false
        }

        if(dialog?.view!!.edtAddAddressAddress1!!.text!!.isEmpty()) {
            dialog?.view!!.edtAddAddressAddress1!!.error = context!!.getString(R.string.add_addresses_address_empty)
            return false
        }


        if(dialog?.view!!.edtAddAddressPhone!!.text!!.isEmpty()) {
            dialog?.view!!.edtAddAddressPhone!!.error = context!!.getString(R.string.add_addresses_phone_empty)
            return false
        }

        return true
    }
}

interface AddAddressDialogListener {
    fun onSubmit(alias: String, address1: String, address2: String,
                 country: String, zip: String, phone: String)
}

