package com.example.imedical.core.platform

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T : ViewModel>@Inject constructor(val viewModel: T) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}