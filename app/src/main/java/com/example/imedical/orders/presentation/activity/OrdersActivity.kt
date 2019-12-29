package com.example.imedical.orders.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.imedical.R
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.orders.presentation.adapter.OrdersAdapter
import com.example.imedical.orders.presentation.viewmodel.OrdersViewModel
import kotlinx.android.synthetic.main.activity_orders.*
import javax.inject.Inject

class OrdersActivity : BaseActivity() {

    @Inject
    lateinit var ordersViewModelFactory: ViewModelFactory<OrdersViewModel>
    private val ordersViewModel by lazy {
        ViewModelProviders.of(this, ordersViewModelFactory).get(OrdersViewModel::class.java)
    }
    private var page = 1
    private var lastPage = 1
    private var loading = true
    private val ordersAdapter by lazy { OrdersAdapter(ArrayList(), this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_orders)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Orders"
        setupRecyclerView()
        ordersViewModel.getOrders(page)
        showProgress()
        observeOrders()
    }

    private fun setupRecyclerView(){
        ordersRecyclerView.adapter = ordersAdapter
        val lm  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ordersRecyclerView.layoutManager = lm
        ordersRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(lm.findLastCompletelyVisibleItemPosition() == ordersAdapter.orders.size -1
                    && page != 1 && !loading && lastPage != page) {
                    loading = true
                    showProgress()
                    ordersViewModel.getOrders(page)
                }
            }
        })
    }
    private fun observeOrders(){
        ordersViewModel.ordersLiveData.observe(this, Observer {
            hideProgress()
            if(it?.status == true){
                if(it.data?.orders?.size == 0 && it.data.currentPage == 1)
                    noOrdersTextView.visibility = View.VISIBLE
                else{
                    lastPage = it.data?.lastPage?:1
                    if(it.data?.currentPage == 1)
                        ordersAdapter.orders.clear()

                    ordersAdapter.orders.addAll(it.data!!.orders)
                    ordersAdapter.notifyDataSetChanged()
                    page = it.data.currentPage

                }
            } else showMessage(it?.error)
            loading = false
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
