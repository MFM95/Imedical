package com.example.imedical.cart.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.example.imedical.R
import com.example.imedical.cart.domain.model.CartItemModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class CartItemsAdapter(
    private val context: Context,
    val items: ArrayList<CartItemModel>,
    private val cartActions: CartActions)

    : RecyclerSwipeAdapter<CartItemsAdapter.CartItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CartItemHolder {
        return CartItemHolder(
            LayoutInflater.from(context).inflate(R.layout.item_cart_element, parent, false), context, cartActions)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(itemHolder: CartItemHolder, position: Int) {
        itemHolder.bind(items[position], position)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }
    class CartItemHolder(private val view: View, private val context: Context, private val cartActions: CartActions)
        : RecyclerView.ViewHolder(view){

        fun bind(item: CartItemModel, position: Int){
            val quantity = view.findViewById<TextView>(R.id.itemCartQuantity)
            Picasso.with(context)
                .load(item.cover)
                .transform(RoundedCornersTransformation(30, 2))
                .into(view.findViewById<ImageView>(R.id.itemCartImageView))

            view.findViewById<TextView>(R.id.itemCartLabelTextView).text = item.name
            view.findViewById<TextView>(R.id.itemCartPriceTextView).text = item.price.toString() + " LE"
            quantity.text = item.quantity.toString()
            //Increase quantity
            view.findViewById<ImageView>(R.id.itemCartIncrease).setOnClickListener {
                quantity.text.toString().toIntOrNull()?.let {
                    quantity.text = (it + 1).toString()
                    cartActions.updateItem(item, it + 1)
                    item.quantity = it + 1
                    cartActions.addToTotal(item.price)
                }
            }
            //Decrease quantity
            view.findViewById<ImageView>(R.id.itemCartDecrease).setOnClickListener {
                quantity.text.toString().toIntOrNull()?.let {
                    if(it > 1) {
                        quantity.text = (it - 1).toString()
                        item.quantity = (it - 1)
                        cartActions.updateItem(item, it - 1)
                        cartActions.removeFromTotal(item.price)
                    }
                }
            }

            view.findViewById<ImageView>(R.id.trash).setOnClickListener {
                cartActions.deleteItem(item, position)
            }
        }
    }
    interface CartActions{
        fun addToTotal(num: Int)
        fun removeFromTotal(num: Int)
        fun updateItem(item: CartItemModel, quantity: Int)
        fun deleteItem(item: CartItemModel, position: Int)
    }
}