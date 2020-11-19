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

        btn.setOnClickListener {
            ILFactory.getLoader().load(this, imageview, "https://wx3.sinaimg.cn/mw1024/007Ohqdmly1gkqz523n1jj30qo0f0gpb.jpg", LoaderOptions())
        }


//        val list = ArrayList<String>()

//        list.add("https://github.com/ifmvo/SomeImage/blob/master/banner31.png?raw=true")
//        list.add("https://github.com/ifmvo/SomeImage/blob/master/banner3.png?raw=true")
//
//        ILFactory.getLoader().load(this, imageview, list, LoaderOptions().defaultOptions(), object : LoadListener(){
//            override fun onLoadCompleted(drawable: Drawable?): Boolean {
//                Log.e("ifmvo", "onLoadCompleted")
//                return false
//            }
//
//            override fun onLoadFailed(e: Throwable?): Boolean {
//                Log.e("ifmvo", "onLoadFailed")
//                return super.onLoadFailed(e)
//            }
//
//            override fun onLoadProgress(progress: Int) {
//                super.onLoadProgress(progress)
//            }
//        })

    }
}
