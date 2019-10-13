package com.example.imedical.core.ui

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import com.example.imedical.R

object CustomAlertDialog {

    fun show(context: Context, message: String?, positive_button_text: String) {
        show(context, null, message, positive_button_text, null, null, null)
    }

    fun show(context: Context, message: String?, positive_button_text: String?, negative_button_text: String?, clickListenerPositiveButton: View.OnClickListener?, clickListenerNegativeButton: View.OnClickListener?) {
        show(context, null, message, positive_button_text, negative_button_text, clickListenerPositiveButton, clickListenerNegativeButton)
    }

    fun show(context: Context, message: String?, positive_button_text: String?, clickListenerPositiveButton: View.OnClickListener) {
        show(context, null, message, positive_button_text, null, clickListenerPositiveButton, null)
    }

    fun show(context: Context, title: String?, message: String?, positive_button_text: String?, negative_button_text: String?, clickListenerPositiveButton: View.OnClickListener?, clickListenerNegativeButton: View.OnClickListener?) {
        val alertDialog: AlertDialog? = context?.let {
            val builder = AlertDialog.Builder(context, R.style.Base_Theme_AppCompat_Light_Dialog)
            builder.apply {
                setPositiveButton(positive_button_text
                ) { dialog, id ->
                    // User clicked OK button
                    clickListenerPositiveButton?.onClick(null)
                    dialog.dismiss()
                }
                setNegativeButton(negative_button_text) { dialog, _ ->
                    // User clicked OK button
                    clickListenerNegativeButton?.onClick(null)
                    dialog.dismiss()
                }
            }
            // Set other dialog properties
            builder.setTitle(title)
            builder.setMessage(message)
            // Create the AlertDialog
            builder.create()


        }
        alertDialog?.show()
    }

    fun show(context: Context, message: String?, positive_button_text: String?, neural_button_text: String?, negative_button_text: String?, clickListenerPositiveButton: View.OnClickListener?, clickListenerNeuralButton: View.OnClickListener?, clickListenerNegativeButton: View.OnClickListener?) {
        val alertDialog: AlertDialog? = context?.let {
            val builder = AlertDialog.Builder(context, R.style.Base_Theme_AppCompat_Light_Dialog)
            builder.apply {
                setPositiveButton(positive_button_text)
                { dialog, id ->
                    clickListenerPositiveButton?.onClick(null)
                    dialog.dismiss()
                }

                setNeutralButton(neural_button_text)
                { dialog, _ ->
                    clickListenerNeuralButton?.onClick(null)
                    dialog.dismiss()
                }
                setNegativeButton(negative_button_text)
                { dialog, _ ->
                    clickListenerNegativeButton?.onClick(null)
                    dialog.dismiss()
                }
            }
            // Set other dialog properties
            builder.setMessage(message)
            // Create the AlertDialog
            builder.create()

        }
        alertDialog?.show()
    }
}

