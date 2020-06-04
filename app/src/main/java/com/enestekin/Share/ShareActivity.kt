package com.enestekin.Share

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_home.*

class ShareActivity : AppCompatActivity() {

    private val activityNumber = 2
    private val TAG = "Share Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupNavigationView()


    }

    fun setupNavigationView () {

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this,bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(activityNumber)
        menuItem.setChecked(true)

    }

}
