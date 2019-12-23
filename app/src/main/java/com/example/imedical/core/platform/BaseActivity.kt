package com.example.imedical.core.platform

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.imedical.AndroidApplication
import com.example.imedical.core.UserPreferences
import com.example.imedical.core.di.component.ApplicationComponent
import com.example.imedical.core.ui.LoadingDialog
import javax.inject.Inject


/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: LoadingDialog? = null

    @Inject lateinit var userPreferences: UserPreferences

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

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
        progressDialog = LoadingDialog(this)
    }

    protected fun showSnack(view: View, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG).show()
    }
}