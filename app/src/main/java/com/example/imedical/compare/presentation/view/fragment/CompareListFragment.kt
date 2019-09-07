package com.example.imedical.compare.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.imedical.R
import com.example.imedical.compare.domain.model.ProductModel
import com.example.imedical.compare.presentation.view.adapter.CompareListAdapter
import com.example.imedical.compare.presentation.view.adapter.ICompareListCallback
import com.example.imedical.compare.presentation.viewmodel.CompareListViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import javax.inject.Inject


class CompareListFragment : BaseFragment() {


    private lateinit var compareViewModel: CompareListViewModel

    @Inject
    lateinit var compareViewModelFactory: ViewModelFactory<CompareListViewModel>

    private lateinit var compareListAdapter: CompareListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                it?.let {
                    setUpGridView(it)
                }
            })
    }

    private fun setUpGridView(compareList: ArrayList<ProductModel>) {
        compareListAdapter = CompareListAdapter(compareList, CompareListCallback(), activity!!)
    }


    class CompareListCallback: ICompareListCallback {
        override fun onProductClick(productModel: ProductModel) {
        }

        override fun onWishClick(id: Int) {
        }

        override fun addToCart(id: Int) {
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() =
            CompareListFragment().apply {

                }
            }
}
