package com.ifmvo.testLoader

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ifmvo.imageloader.ILFactory
import com.ifmvo.imageloader.LoadListener
import com.ifmvo.imageloader.progress.LoaderOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val url = "https://github.com/ifmvo/SomeImage/blob/master/banner3.png?raw=true"

        ILFactory.getLoader().load(this, imageview, url, LoaderOptions().defaultOptions())

        ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round))

        ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache())

        ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache().circle())

        ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache().circle(), object : LoadListener(){
            override fun onLoadCompleted(drawable: Drawable?): Boolean {
                return false
            }

            override fun onLoadFailed(e: Throwable?): Boolean {
                return super.onLoadFailed(e)
            }

            override fun onLoadProgress(progress: Int) {
                super.onLoadProgress(progress)
            }
        })

    }
}
