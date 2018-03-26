package com.ifmvo.imageloader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.ifmvo.imageloader.progress.LoaderOptions

class MainActivity : AppCompatActivity() {

    lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        ILFactory.getLoader().load(this, image, "https://github.com/ifmvo/SomeImage/blob/master/banner3.png?raw=true", LoaderOptions().defaultOptions())

    }
}
