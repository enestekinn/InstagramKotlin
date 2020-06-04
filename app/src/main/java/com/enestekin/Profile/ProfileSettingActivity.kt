package com.enestekin.Profile

import android.content.Intent
import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.BottomNavigationViewHelper
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.activity_profile_setting.*


class ProfileSettingActivity : AppCompatActivity() {

    private val activityNumber = 4
    private val TAG = "Profile Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting)

        setupNavigationView()
        setupFragmentNavigations()



    }

fun setupFragmentNavigations () {


    tvFragmentEdit.setOnClickListener {
        profileSettingsRoots.visibility=View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.profileSettingsCointainer,ProfileEditFragment())
        transaction.addToBackStack("EditProfileOKAY")
        transaction.commit()

    }
    textViewSignOut.setOnClickListener {
        profileSettingsRoots.visibility=View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.profileSettingsCointainer,SignOutFragment())
        transaction.addToBackStack("SignOutOKAY")
        transaction.commit()
    }


}


    fun setupNavigationView () {

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this,bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(activityNumber)
        menuItem.setChecked(true)

    }


    fun imgBack (view : View) {
        onBackPressed()
        overridePendingTransition(0,0)


    }

    override fun onBackPressed() {
        profileSettingsRoots.visibility=View.VISIBLE
        super.onBackPressed()
    }
}
