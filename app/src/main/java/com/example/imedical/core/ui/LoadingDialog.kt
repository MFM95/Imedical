package com.example.imedical.core.ui

import android.animation.Animator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.imedical.R
import kotlinx.android.synthetic.main.item_loading.*

class LoadingDialog(context: Context): Dialog(context, R.style.CustomDialogTheme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_loading)
        setCancelable(false)
        animateSecond()
    }

    private fun animateSecond(){
        val animator = ivLoading2.animate().alpha(1.0f)
        animator.duration = 600
        animator.start()
        animator.setListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                animateThird()
            }
        })
    }
    private fun animateThird(){
        val animator = ivLoading3.animate().alpha(1.0f)
        animator.duration = 600
        animator.start()
        animator.setListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                animateFourth()
            }
        })
    }
    private fun animateFourth(){
        val animator = ivLoading4.animate().alpha(1.0f)
        animator.duration = 600
        animator.start()
        animator.setListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                ivLoading2.alpha = 0.0f
                ivLoading3.alpha = 0.0f
                ivLoading4.alpha = 0.0f
                animateSecond()
            }
        })
    }
}