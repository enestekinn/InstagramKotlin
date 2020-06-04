package com.enestekin.utils

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.enestekin.Home.HomeActivity
import com.enestekin.News.News
import com.enestekin.Profile.Profile
import com.enestekin.Search.Search
import com.enestekin.Share.ShareActivity
import com.enestekin.instagramkotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
class BottomNavigationViewHelper {

    companion object  {

        fun setupBottomNavigationView( bottomNavigationViewEx: BottomNavigationViewEx) {


            bottomNavigationViewEx.enableAnimation(false)
            bottomNavigationViewEx.enableShiftingMode(false)
            bottomNavigationViewEx.enableItemShiftingMode(false)
            bottomNavigationViewEx.setTextVisibility(false)
            bottomNavigationViewEx.setIconSize(30f)

        }

        fun setupNavigation (context: Context ,bottomNavigationViewEx: BottomNavigationViewEx) {

            bottomNavigationViewEx.onNavigationItemSelectedListener =object :BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.ic_home -> {
                            val intent = Intent(context, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true

                        }
                        R.id.ic_search -> {
                            val intent = Intent(context, Search::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true

                        }
                        R.id.ic_share -> {
                            val intent = Intent(context, ShareActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true

                        }
                        R.id.ic_olay -> {
                            val intent = Intent(context, News::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)  //aktivitiy gecislerindeki animasyonu kapatiyor
                            return true

                        }
                        R.id.ic_profile -> {
                            val intent = Intent(context, Profile::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                            return true


                        }


                    }
                    return false
                }
            }

            }

        }

    }

