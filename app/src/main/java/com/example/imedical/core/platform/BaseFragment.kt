package com.example.imedical.core.platform

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.imedical.R
import com.example.imedical.core.UserPreferences
import com.example.imedical.core.di.component.ApplicationComponent
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseFragment : Fragment(){

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as BaseActivity).appComponent
    }

    @Inject
    lateinit var userPreferences: UserPreferences

    protected fun showMessage(message: String?){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    protected fun showConnectionError(){
        Snackbar.make(view!!, R.string.check_connection, Snackbar.LENGTH_LONG)
    }
}