package com.enestekin.Profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.UniversialImageLoader
import com.nostra13.universalimageloader.core.ImageLoader
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*


class ProfileEditFragment : Fragment() {
    lateinit var circleImageFragment : CircleImageView      // progress bar ile circler view muzu sonradan degerini vermek
    lateinit var  progressBar: ProgressBar                  // uzeri initilize ettik lateinit ile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




val view = inflater.inflate(R.layout.fragment_profile_edit, container, false)
        // fragment icerisindeki xml li kullanmak icin bir view degiskenine atadik

        circleImageFragment= view?.findViewById(R.id.circleImageProfile)!!   // burada da onlari tanimladik
      progressBar=view?.findViewById(R.id.progressBar2)!!

        setupProfilePicture()


        view.imageView.setOnClickListener {

            activity!!.onBackPressed()  // fragmentlerde boyle erisiliyor

        }

        return view
        }



    private fun setupProfilePicture() {
       // var imgURL = "cdn.webrazzi.com/uploads/2019/09/apple-eylul-2019-516_hd_hd_hd_hd_hd_hd.jpeg"
        var imgURL = "cdn.yeniakit.com.tr/images/news/625/android-ne-demektir-h1475659734-c4766e.jpg"
        UniversialImageLoader.setImage(imgURL,circleImageFragment, progressBar, "https://" )

    }


}










