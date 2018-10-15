package com.ifmvo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.ifmvo.imageloader.progress.LoaderOptions;

import java.io.File;


/**
 * Created by wanglei on 2016/11/27.
 */

public interface ILoader {

    void load(Context context, ImageView target, String ... urlArr);

    void load(Context context, ImageView target, LoaderOptions loaderOptions, String ... urlArr);

    void load(Context context, ImageView target, String thumbnail, LoaderOptions loaderOptions, String ... urlArr);

    void load(Context context, ImageView target, LoaderOptions loaderOptions, LoadListener loadListener, String ... urlArr);

    void load(Context context, ImageView target, String thumbnail, LoaderOptions loaderOptions, LoadListener loadListener, String ... urlArr);

    void loadAssets(ImageView target, String assetName);

    void loadFile(ImageView target, File file);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    String getCacheSize(Context context);

    void resume(Context context);

    void pause(Context context);

}
