package com.example.imedical.cart.presentation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.cart.domain.model.CartItemModel
import com.example.imedical.cart.domain.model.CartModel
import com.example.imedical.cart.presentation.view.adapter.CartItemsAdapter
import com.example.imedical.cart.presentation.viewmodel.CartViewModel
import com.example.imedical.core.platform.BaseFragment
import com.example.imedical.core.platform.ViewModelFactory
import com.example.imedical.home.presentation.view.activity.CheckoutActivity
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CartViewModel>
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CartViewModel::class.java)
    }

    private lateinit var adapter: CartItemsAdapter
    private var itemPositionToRemove = -1
    private var itemPositionToUpdate = -1
    private var subtotal: Float? = 0.0f
    private var total: Float? = 0.0f

    private val CHECKOUT_REQ = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        observeCart()
        getCart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        checkoutBtn.setOnClickListener {
            startActivityForResult(Intent(activity, CheckoutActivity::class.java), CHECKOUT_REQ)
        }
    }

    private fun setupRecycler() {
        adapter = CartItemsAdapter(context!!, ArrayList(), cartActions)
        cartItemsRecyclerView.adapter = adapter
        cartItemsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeCart() {
        viewModel.getCartLiveData().observe(this, Observer {
            hideProgress()
            it?.let {
                if (it.status) {
                    if(it.data != null)
                        bindCartData(it.data)
                } else showMessage(it.error)
            }
        })

        viewModel.getRemoveFromCartLiveData().observe(this, Observer {
            hideProgress()
            if(it?.status != true)
                showMessage(it?.error)
            it?.let {
                if(it.status && itemPositionToRemove != -1){
                    it.data?.let { data -> bindCartData(data) }
                    adapter.items.removeAt(itemPositionToRemove)
                    adapter.notifyItemRemoved(itemPositionToRemove)
                    itemPositionToRemove = -1
                } else showMessage(it.error)
            }
        })

        viewModel.getUpdateCartLiveData().observe(this, Observer {
            hideProgress()
            if(it?.status != true)
                showMessage(it?.error)
            it?.data?.let { data ->
                bindCartData(it.data)
                if(itemPositionToUpdate != -1) {
                    for (p in data.cartItems) {
                        if(p.id == adapter.items[itemPositionToUpdate].id){
                            adapter.items[itemPositionToUpdate].quantity = p.quantity
                            adapter.notifyItemChanged(itemPositionToUpdate)
                            break
                        }
                    }
                }
            }
        })

    }

    private fun bindCartData(cartModel: CartModel) {
        var size  = adapter.items.size
        adapter.items.clear()
        adapter.notifyItemRangeRemoved(0, size)

        size = cartModel.cartItems.size
        adapter.items.addAll(cartModel.cartItems)
        adapter.notifyItemRangeInserted(0, size)

        cartShippingTextView.text = String.format("%s LE", cartModel.shippingFee)
        cartTaxTextView.text = String.format("%s LE", cartModel.tax)
        cartSubtotalTextView.text = String.format("%s LE", cartModel.subtotal)
        cartTotalTextView.text = String.format("%s LE", cartModel.total)
        subtotal =  cartModel.subtotal.toFloatOrNull()
        total = cartModel.total.toFloatOrNull()

    }

    private fun getCart() {
        viewModel.updateCart()
    }

    private val cartActions = object : CartItemsAdapter.CartActions{
        override fun removeFromTotal(num: Int) {
            if(total == null)
                total = 0.0f
            if(subtotal == null)
                subtotal = 0.0f
                if(subtotal!! - num > 0)
                    cartSubtotalTextView.text = String.format("%s LE", (subtotal!! - num).toString())

                if(total!! - num > 0)
                    cartTotalTextView.text = String.format("%s LE", (total!! - num).toString())
        }

        override fun addToTotal(num: Int) {
            if(total == null)
                total = 0.0f
            if(subtotal == null)
                subtotal = 0.0f

                cartSubtotalTextView.text = String.format("%s LE", (subtotal!! + num).toString())

                cartTotalTextView.text = String.format("%s LE", (total!! + num).toString())
        }

        override fun updateItem(item: CartItemModel, quantity: Int, position: Int) {
            showProgress()
            viewModel.updateCartItem(item.rowId, quantity)
            itemPositionToUpdate = position
        }

        override fun deleteItem(item: CartItemModel, position: Int) {
            showProgress()
            viewModel.removeFromCart(item.rowId)
            itemPositionToRemove = position
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CHECKOUT_REQ && data != null) {
            if(data.getBooleanExtra("success", false)){
                activity?.finish()
            }
        }
    }
}
