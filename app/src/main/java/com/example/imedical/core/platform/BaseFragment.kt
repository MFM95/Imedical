package com.example.imedical.core.platform

import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.imedical.core.di.component.ApplicationComponent

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseFragment : Fragment(){

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as BaseActivity).appComponent
    }

    protected fun showMessage(message: String?){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}