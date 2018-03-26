package com.ifmvo.imageloader;//package cn.ifmvo.imageloader;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.Nullable;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestBuilder;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//
//import java.io.File;
//
//import cn.ifmvo.imageloader.circle.GlideCircleTransform;
//import cn.ifmvo.imageloader.progress.LoaderOptions;
//import cn.ifmvo.imageloader.progress.OnProgressListener;
//import cn.ifmvo.imageloader.progress.ProgressManager;
//
///**
// *
// * Created by 陈序员 on 2017/10/9.
// */
//class Glide4 implements ILoader {
//
//    private RequestBuilder<Drawable> requestBuilder;
//    private String currentUrl;
//
//
//    @Override
//    public ILoader load(Context context, String url) {
//        requestBuilder = Glide.with(context).load(url);
//        currentUrl = url;
//        return this;
//    }
//
//    @Override
//    public ILoader apply(LoaderOptions loaderOptions) {
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.error(loaderOptions.getIconErrorRes());
//        requestOptions.placeholder(loaderOptions.getIconLoadingRes());
//        if (loaderOptions.isCircle()){
//            requestOptions.transform(new GlideCircleTransform());
//        }
//        requestBuilder.apply(requestOptions);
//        return this;
//    }
//
//    @Override
//    public ILoader listener(final LoadListener loadListener) {
//        /*
//         * 进度的监听的处理
//         */
//        final OnProgressListener onProgressListener = new OnProgressListener() {
//            @Override
//            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
//                if(loadListener != null){
//                    loadListener.onLoadProgress((int) ((bytesRead * 1.0f / totalBytes) * 100.0f));
//                }
//                if (isDone){
//                    ProgressManager.removeProgressListener(imageUrl, this);
//                }
//            }
//        };
//
//        ProgressManager.addProgressListener(currentUrl, onProgressListener);
//
//        /*
//         * 成功和失败的处理
//         */
//        requestBuilder.listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                if (loadListener != null){
//                    loadListener.onLoadFailed(e);
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                if (loadListener != null){
//                    loadListener.onLoadCompleted(resource);
//                }
//                return false;
//            }
//        });
//        return this;
//    }
//
//    @Override
//    public void into(ImageView targetView) {
//        requestBuilder.into(targetView);
//    }
//
//
//    @Override
//    public void loadAssets(ImageView target, String assetName) {
//
//    }
//
//    @Override
//    public void loadFile(ImageView target, File file) {
//
//    }
//
//    @Override
//    public void clearMemoryCache(Context context) {
//
//    }
//
//    @Override
//    public void clearDiskCache(Context context) {
//
//    }
//
//    @Override
//    public void resume(Context context) {
//
//    }
//
//    @Override
//    public void pause(Context context) {
//
//    }
//}
