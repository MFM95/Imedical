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
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(
    private val categoriesList: ArrayList<CategoryModel>,
    private val context: Context
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    val finalCategoryClickedLiveData by lazy { MutableLiveData<Int>() }
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CategoriesHolder {
        return CategoriesHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_category, viewGroup, false), context
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.bind(categoriesList[position], position)
    }


    inner class CategoriesHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        fun bind(categoryModel: CategoryModel, position: Int) {
            view.tvCategoryItemName.text = categoryModel.name ?: ""
            if (categoryModel.childrenCount!! > 0) {
                view.imgCategoryItemArrow.visibility - View.VISIBLE
            } else {
                view.imgCategoryItemArrow.visibility = View.INVISIBLE
            }
            categoryModel.children?.let { children ->
                val categoriesAdapter = FirstChildrenAdapter(children, context)
                categoriesAdapter.finalCategoryClickedLiveData.observe(view.context as LifecycleOwner, Observer {
                    finalCategoryClickedLiveData.value = it
                })
                view.rvCategoryItemChildren.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                view.rvCategoryItemChildren.adapter = categoriesAdapter
            }

            view.lyCategoryItemTitle.setOnClickListener {
                if (categoryModel.childrenCount > 0) {
                    if (categoryModel.isExpanded!!) {
                        view.rvCategoryItemChildren.visibility = View.GONE
                        view.imgCategoryItemArrow.setImageResource(R.drawable.ic_see_all)
                        categoryModel.isExpanded = false
                    } else {
                        view.rvCategoryItemChildren.visibility = View.VISIBLE
                        view.imgCategoryItemArrow.setImageResource(R.drawable.ic_see_all)
                        categoryModel.isExpanded = true
                    }
                } else {
                    finalCategoryClickedLiveData.value = categoryModel.id
                }
            }
        }
    }
}