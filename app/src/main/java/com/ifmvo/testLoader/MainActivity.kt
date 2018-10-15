package com.ifmvo.testLoader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ifmvo.imageloader.ILFactory
import com.ifmvo.imageloader.progress.LoaderOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ILFactory.getLoader().load(this, image, "https://github.com/ifmvo/SomeImage/blob/master/banner3.png?raw=true", LoaderOptions().defaultOptions())

    }
}
