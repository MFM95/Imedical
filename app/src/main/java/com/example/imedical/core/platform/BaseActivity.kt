package com.example.imedical.core.platform

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.imedical.AndroidApplication
import com.example.imedical.core.di.component.ApplicationComponent

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
abstract class BaseActivity : AppCompatActivity() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    protected fun showMessage(message: String?){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}