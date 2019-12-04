package com.example.imedical.categories.presentation.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.imedical.R
import com.example.imedical.categories.domain.model.CategoryModel
import com.example.imedical.categories.presentation.viewmodel.CategoriesViewModel
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.core.platform.ViewModelFactory
import javax.inject.Inject

class CategoriesActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CategoriesViewModel>
    lateinit var viewModel: CategoriesViewModel

    private var studentChildren: ArrayList<CategoryModel>? = null
    private var studentSecondChildren: ArrayList<Pair<Int, CategoryModel>>? = null
    private var doctorsChildren: ArrayList<CategoryModel>? = null
    private var doctorsSecondChildren: ArrayList<Pair<Int, CategoryModel>>? = null

    private var studentsIds = ArrayList<Int>()
    private var doctorsIds = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_categories)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

        getCategories()
    }

    private fun getCategories() {
        // to get Doctor's children, call getAllCategories
        // and remove Student category from them
        //
        // to get Student's children, call getChildren with id 40
        //
        // then if childrenCount > 0, call getChildren with child id

        viewModel.getAllCategories()
        viewModel.getCategoriesLiveData().observe(this, Observer {
            it?.let {
                doctorsChildren = ArrayList()
                for (child in it) {
                    if(child.id != 40) {
                        doctorsChildren!!.add(child)
                        doctorsIds.add(child.id!!)
                    }
                }
                if(!doctorsChildren.isNullOrEmpty() && !studentChildren.isNullOrEmpty()) {
                    getChildren()
                }
             }
        })


        viewModel.getCategoryChildren(40)
        viewModel.getCategoryChildrenLiveData().observe(this, Observer {
            it?.let {
                studentChildren = ArrayList()
                if (it.first == 40) {
                    for (child in it.second) {
                        studentChildren!!.add(child)
                        studentsIds.add(child.id!!)
                    }

                }
                if(!doctorsChildren.isNullOrEmpty() && !studentChildren.isNullOrEmpty()) {
                    getChildren()
                }
            }
        })


    }

    private fun getChildren() {
        studentChildren?.let {
            for(child in it) {
                viewModel.getCategoryChildren(child.id!!)
            }
        }
        doctorsChildren?.let {
            for(child in it) {
                viewModel.getCategoryChildren(child.id!!)
            }
        }
        viewModel.getCategoryChildrenLiveData().observe(this, Observer {
            it?.let {
                if(studentsIds.contains(it.first)) {
                    for(secondChild in it.second) {
                        studentSecondChildren!!.add(Pair(it.first, secondChild))
                    }
                } else if(doctorsIds.contains(it.first)) {
                    for (secondChild in it.second) {
                        doctorsSecondChildren!!.add(Pair(it.first, secondChild))
                    }
                }

                Log.v("Category", "doctors: ${doctorsChildren!!.size}")
                Log.v("Category", "students: ${studentChildren!!.size}")
                Log.v("Category", "doctorsSecond: ${doctorsSecondChildren!!.size}")
                Log.v("Category", "studentsSecond: ${studentSecondChildren!!.size}")
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent
                = Intent(context, CategoriesActivity::class.java)
    }
}
