package com.enestekin.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.enestekin.instagramkotlin.R
import kotlinx.android.synthetic.main.activity_home.view.*

class CameraFragment : Fragment ( ) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater?.inflate(R.layout.fragment_camera,container,false)

        return view
    }
}