package com.example.imedical.home.presentation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.adapter.VendorsAdapter
import com.example.imedical.home.presentation.viewmodel.VendorsViewModel
import kotlinx.android.synthetic.main.fragment_vendors.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class VendorsFragment : BaseFragment() {

    @Inject
    lateinit var vendorsViewModelFactory: ViewModelFactory<VendorsViewModel>
    private val vendorsViewModel by lazy {
        ViewModelProviders.of(this, vendorsViewModelFactory).get(VendorsViewModel::class.java)
    }

    private val vendorsAdapter by lazy { VendorsAdapter(ArrayList(), activity!!) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        observeVendors()
        showProgress()
        vendorsViewModel.getVendors()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vendors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
    }
    private fun observeVendors(){
        vendorsViewModel.getVendorsLiveData.observe(this, Observer {
            hideProgress()
            if(it?.status == true && it.data != null){
                vendorsAdapter.vendors.clear()
                vendorsAdapter.vendors.addAll(it.data.vendors)
                vendorsAdapter.notifyDataSetChanged()
            } else showMessage(it?.error)
        })
    }

    private fun setupRecycler(){
        vendorRecyclerView.adapter = vendorsAdapter
        vendorRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}
