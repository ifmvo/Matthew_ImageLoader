package com.ifmvo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.ifmvo.imageloader.progress.LoaderOptions;

import java.io.File;


/**
 * Created by wanglei on 2016/11/27.
 */

public interface ILoader {

//    ILoader init(Context context);

//    void load(Context context, ImageView target, String url);

//    void load(Context context, ImageView target, String url, String thumbnail);

    void load(Context context, ImageView target, String url, LoaderOptions loaderOptions);

    void load(Context context, ImageView target, String url, String thumbnail, LoaderOptions loaderOptions);

    void load(Context context, ImageView target, String url, LoaderOptions loaderOptions, LoadListener loadListener);

    void load(Context context, ImageView target, String url, String thumbnail, LoaderOptions loaderOptions, LoadListener loadListener);

    void loadAssets(ImageView target, String assetName);

    void loadFile(ImageView target, File file);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    String getCacheSize(Context context);

    void resume(Context context);

    void pause(Context context);

}
