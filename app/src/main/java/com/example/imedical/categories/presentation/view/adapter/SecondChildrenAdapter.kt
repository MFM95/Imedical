package com.example.imedical.categories.presentation.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.domain.model.SecondChildren
import kotlinx.android.synthetic.main.item_category_second_children.view.*

class SecondChildrenAdapter(private val categoriesList: ArrayList<SecondChildren>,
                            private val context: Context)
    : RecyclerView.Adapter<SecondChildrenAdapter.SecondChildrenHolder>() {

    val finalCategoryClickedLiveData by lazy { MutableLiveData<Int>() }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SecondChildrenHolder {
        return SecondChildrenHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_category_second_children, viewGroup, false), context)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: SecondChildrenHolder, position: Int) {
        holder.bind(categoriesList[position], position)
    }


    inner class SecondChildrenHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        fun bind(categoryModel: SecondChildren, position: Int) {
            view.tvCategorySecondChildrenItemName.text = categoryModel.name?: ""
            if(categoryModel.childrenCount!! > 0) {
                view.imgCategorySecondChildrenItemArrow.visibility - View.VISIBLE
            } else {
                view.imgCategorySecondChildrenItemArrow.visibility = View.INVISIBLE
            }
            view.lyCategorySecondChildrenItemTitle.setOnClickListener {
                finalCategoryClickedLiveData.value = categoryModel.id
            }
        }
    }
}