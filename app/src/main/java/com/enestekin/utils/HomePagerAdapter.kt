package com.enestekin.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.enestekin.Home.HomeFragment


class HomePagerAdapter (manager: FragmentManager) : FragmentPagerAdapter(manager ,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private  val mFragmentList : ArrayList<Fragment> = ArrayList( ) //Tum fragmentleri tutacak ARRAY LIST


    override fun getItem(position: Int): Fragment {

         return  mFragmentList.get(position)
    }

    override fun getCount(): Int {


        return  mFragmentList.size
    }


fun addFragment (fragment : Fragment) {
    mFragmentList.add(fragment)
}

}