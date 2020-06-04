package com.enestekin.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.enestekin.instagramkotlin.R
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener

class UniversialImageLoader (val mContext : Context){

    val config : ImageLoaderConfiguration
        get() {
            val defaultOptions  = DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true)  // resmi hafizada tutuyor
                .cacheOnDisk(true).resetViewBeforeLoading(true) // yuklemeden once  var olani sifirlar
                .imageScaleType(ImageScaleType.EXACTLY) // resmin boyutu nasil olsun
                .displayer(FadeInBitmapDisplayer(400)).build()

            return ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(WeakMemoryCache())
                .memoryCacheSize(1000*1024*2014).build()
        }

    companion object {

       private val defaultImage = R.drawable.ic_profile

        fun setImage(imgURL : String,imageView  : ImageView,mProgressBar :ProgressBar,ilkKisim : String?) {

            val imageLoader = ImageLoader.getInstance()
            imageLoader.displayImage(ilkKisim+imgURL,imageView,object :ImageLoadingListener{
                override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {

                        if(mProgressBar !=null)
                       mProgressBar.visibility=View.GONE // yuklenme tamamlandiginda progress bari kapat

                }

                override fun onLoadingStarted(imageUri: String?, view: View?) {
                    mProgressBar.visibility=View.VISIBLE
                }

                override fun onLoadingCancelled(imageUri: String?, view: View?) {
                    mProgressBar.visibility=View.GONE
                }

                override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {
                    mProgressBar.visibility=View.GONE
                }

            })

        }
    }
}