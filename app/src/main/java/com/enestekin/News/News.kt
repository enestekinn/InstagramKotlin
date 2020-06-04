package com.enestekin.News

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_home.*

class News : AppCompatActivity() {

    private val activityNumber = 3
    private val TAG = "News Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       // setupNavigationView()


    }

    fun setupNavigationView () {

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this,bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(activityNumber)
        menuItem.setChecked(true)

    }

}
