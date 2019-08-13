package com.example.imedical.core.platform

import android.support.v4.app.Fragment
import com.example.imedical.core.di.component.ApplicationComponent

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseFragment : Fragment(){

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity as BaseActivity).appComponent
    }
}