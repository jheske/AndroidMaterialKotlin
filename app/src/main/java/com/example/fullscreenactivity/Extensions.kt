package com.example.fullscreenactivity

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by jilbot on 07/15/2018
 */

val Fragment.currentActivity: Activity
    get() = this.activity ?: throw IllegalStateException("Activity must not be null")

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.setupToolbar(activityTitle: String? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.setHomeButtonEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = activityTitle.let {
        it
    }
}

fun AppCompatActivity.setupTransparentToolbar(activityTitle: String? = null) {
    setupToolbar(activityTitle)
    supportActionBar?.setDisplayShowTitleEnabled(false)
    supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat
            .getColor(this,
                    android.R.color.transparent)))
}

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View?) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View?) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}

