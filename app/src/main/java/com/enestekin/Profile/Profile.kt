package com.enestekin.Profile

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.BottomNavigationViewHelper
import com.enestekin.utils.UniversialImageLoader

import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile_setting.*


class Profile : AppCompatActivity() {

    private val activityNumber = 4
    private val TAG = "Profile Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupProfilePhoto()
        setupNavigationView()
        setupToolbar()



    }

    private fun setupProfilePhoto() {
        val imageURL = "cdn.yeniakit.com.tr/images/news/625/android-ne-demektir-h1475659734-c4766e.jpg"
        UniversialImageLoader.setImage(imageURL,circleImageProfile,progressBar,"https://")
    }


    private fun setupToolbar() {
        imgProfileSettings.setOnClickListener {
            var intent = Intent(this,ProfileSettingActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    fun setupNavigationView () {

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationViewHelper.setupNavigation(this,bottomNavigationView)
        var menu=bottomNavigationView.menu
        var menuItem = menu.getItem(activityNumber)
        menuItem.setChecked(true)

        profilduzenle.setOnClickListener {
        profileSettingsRoot.visibility=View.GONE
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.profileSettingsCointainerss,ProfileEditFragment())
            transaction.addToBackStack("asd")
            transaction.commit()

        }


    }

    override fun onBackPressed() {
        profileSettingsRoot.visibility=View.VISIBLE
        super.onBackPressed()
    }



    }




