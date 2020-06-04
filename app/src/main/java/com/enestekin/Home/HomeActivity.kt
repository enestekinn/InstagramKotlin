package com.enestekin.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.BottomNavigationViewHelper
import com.enestekin.utils.HomePagerAdapter
import com.enestekin.utils.UniversialImageLoader
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

private val activityNumber = 0
private val TAG = "Home Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    setupNavigationView()
        setupHomeViewPager()
        initImageLoader()

    }

    fun setupNavigationView () {

      BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this,bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(activityNumber)
        menuItem.setChecked(true)

    }
    private fun setupHomeViewPager () {
        var homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        homePagerAdapter.addFragment(CameraFragment()) // id 0
        homePagerAdapter.addFragment(HomeFragment())  // id 1
        homePagerAdapter.addFragment(MessageFragment()) // id 2

        // homeViewPager a olusturdugumuz adapteri bagladik
        homeViewPager.adapter= homePagerAdapter

        homeViewPager.setCurrentItem(1) // homeview pager acildiginda ilk hangi fragment acilsin
    }
    private  fun initImageLoader () {  //birkez calismasi yeterli cagirdimizda diger aktitelerden cagirmamiza gerek yok
        var universialImageLoader = UniversialImageLoader(this)
        ImageLoader.getInstance().init(universialImageLoader.config)
    }
}
