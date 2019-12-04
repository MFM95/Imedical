package com.example.imedical.core.platform

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.example.imedical.AndroidApplication
import com.example.imedical.core.UserPreferences
import com.example.imedical.core.di.component.ApplicationComponent
import javax.inject.Inject

import com.bumptech.glide.Glide
import com.example.imedical.R


/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: Dialog? = null

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject lateinit var userPreferences: UserPreferences

    protected fun showMessage(message: String?){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        if (progressDialog == null) {
            createProgress()
        }

        if (!progressDialog!!.isShowing)
            progressDialog!!.show()
    }

    fun hideProgress() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog!!.dismiss()
    }

    private fun createProgress() {
        progressDialog = Dialog(this, R.style.CustomDialogTheme)
        progressDialog?.setContentView(R.layout.dialog_progress)
        progressDialog?.setCancelable(false)

        val imageView = progressDialog!!.window!!.decorView.findViewById<ImageView>(R.id.progressBar)
        Glide.with(applicationContext)
            .asGif()
            .load(R.drawable.loading_gif)
            .into(imageView)
    }
}