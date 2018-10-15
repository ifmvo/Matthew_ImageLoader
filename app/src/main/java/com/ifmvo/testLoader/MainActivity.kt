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


        val url = "https://github.com/ifmvo/SomeImage/blob/master/banner10.png?raw=true"
        val errorUrl = "https://avatar.csdn.net/0/9/5/1_rabbitbug.jpg"

        ILFactory.getLoader().load(this, imageview, LoaderOptions().defaultOptions(), url, errorUrl)

    }
}
