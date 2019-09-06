package com.example.imedical.compare.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.compare.domain.model.ProductModel
import com.example.imedical.compare.presentation.viewmodel.CompareListViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import javax.inject.Inject


class CompareListFragment : BaseFragment() {


    private lateinit var compareViewModel: CompareListViewModel

    @Inject
    lateinit var compareViewModelFactory: ViewModelFactory<CompareListViewModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compare_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compareViewModel = ViewModelProviders.of(this, compareViewModelFactory).get(CompareListViewModel::class.java)
        getCompareList()
    }

    private fun getCompareList() {
        compareViewModel.getCompareList()
        compareViewModel.getCompareListLiveData().observe(this,
            Observer {
                // todo display compare list
            })
    }

    private fun addToCompareList(product: ProductModel) {
        compareViewModel.addToCompareList(product)
        // todo observe on adding, display success messsage
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CompareListFragment().apply {

                }
            }
}
