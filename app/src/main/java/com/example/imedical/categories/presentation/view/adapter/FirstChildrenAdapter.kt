package com.example.imedical.categories.presentation.view.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imedical.R
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.domain.model.FirstChildren
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_category_first_children.view.*

class FirstChildrenAdapter(
    private val categoriesList: ArrayList<FirstChildren>,
    private val context: Context
) : RecyclerView.Adapter<FirstChildrenAdapter.FirstChildrenHolder>() {

    val finalCategoryClickedLiveData by lazy { MutableLiveData<Int>() }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): FirstChildrenHolder {
        return FirstChildrenHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_category_first_children, viewGroup, false), context
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: FirstChildrenHolder, position: Int) {
        holder.bind(categoriesList[position], position)
    }


    inner class FirstChildrenHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        fun bind(categoryModel: FirstChildren, position: Int) {
            view.tvCategoryFirstChildrenItemName.text = categoryModel.name ?: ""
            if (categoryModel.childrenCount!! > 0) {
                view.imgCategoryFirstChildrenItemArrow.visibility - View.VISIBLE
            } else {
                view.imgCategoryFirstChildrenItemArrow.visibility = View.INVISIBLE
            }
            categoryModel.children?.let { children ->
                val categoriesAdapter = SecondChildrenAdapter(children, context)
                categoriesAdapter.finalCategoryClickedLiveData.observe(view.context as LifecycleOwner, Observer {
                    finalCategoryClickedLiveData.value = it
                })
                view.rvCategoryFirstChildrenItemChildren.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                view.rvCategoryFirstChildrenItemChildren.adapter = categoriesAdapter
            }

            view.lyCategoryFirstChildrenItemTitle.setOnClickListener {
                if (categoryModel.childrenCount > 0) {
                    if (categoryModel.isExpanded!!) {
                        view.rvCategoryFirstChildrenItemChildren.visibility = View.GONE
                        view.imgCategoryFirstChildrenItemArrow.setImageResource(R.drawable.ic_see_all)
                        categoryModel.isExpanded = false
                    } else {
                        view.rvCategoryFirstChildrenItemChildren.visibility = View.VISIBLE
                        view.imgCategoryFirstChildrenItemArrow.setImageResource(R.drawable.ic_see_all)
                        categoryModel.isExpanded = true
                    }
                } else finalCategoryClickedLiveData.value = categoryModel.id
            }
        }
    }
}