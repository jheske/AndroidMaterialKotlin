package com.example.fullscreenactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.os.Build
import android.support.v4.app.Fragment

/**
 * Created by jilbot on 07/14/2018
 *
 * https://stackoverflow.com/questions/50142064/how-to-have-a-transparent-status-bar-but-leave-navigation-bar-opaque
 */

class FullsizeImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = 0x00000000  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            window.addFlags(flags)
        }
        setContentView(R.layout.activity_fullsize_image)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        setupTransparentToolbar()

        val imageResourceId = intent?.getIntExtra(ARG_RESOURCE_ID,R.drawable.oncidium)
        imageResourceId?.let {
            loadFragment(it)
        }
    }

    fun loadFragment(imageResourceId: Int) {
        val fragment: Fragment = FullsizeImageActivityFragment.newInstance(imageResourceId)
        supportFragmentManager.inTransaction {
            add(R.id.fullsize_image_activity_fragment_frame, fragment)
        }
    }
}