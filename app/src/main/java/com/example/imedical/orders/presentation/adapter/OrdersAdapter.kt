package com.example.imedical.orders.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.orders.domain.model.OrderModel
import kotlinx.android.synthetic.main.item_order.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class OrdersAdapter(val orders: ArrayList<OrderModel>, private val context: Context) : RecyclerView.Adapter<OrdersAdapter.OrderHolder>(){
    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): OrderHolder {
        return OrderHolder(
            LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)
        )
    }

    inner class OrderHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(orderModel: OrderModel){
            view.dateTextView.text = getDateString(orderModel.date.date)
            view.orderIdTextView.text = ""  + orderModel.total + " LE"
            view.statusTextView.text = orderModel.status.name
        }
        private fun getDateString(date: String): String{
            val split = date.split("-")
            if(split.size < 3)
                return ""
            val calendar = Calendar.getInstance()

            val year = split[0].toIntOrNull()
            val month = split[1].toIntOrNull()
            var day = split[2].toIntOrNull()
            calendar.set(Calendar.YEAR, year?:1)
            calendar.set(Calendar.MONTH, month?:1)
            calendar.set(Calendar.DAY_OF_MONTH, day?:1)
            val dd = calendar.time.toString().split(" ")
            val arr = ArrayList<String>()
            arr.addAll(dd)
            arr.removeAt(3)
            arr.removeAt(3)
            return arr.joinToString("\n")
        }
    }
}