package com.ifmvo.imageloader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ifmvo.imageloader.circle.GlideCircleTransform;
import com.ifmvo.imageloader.circle.GlideRoundTransform;
import com.ifmvo.imageloader.progress.LoaderOptions;
import com.ifmvo.imageloader.progress.OnProgressListener;
import com.ifmvo.imageloader.progress.ProgressManager;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by 陈序员 on 2017/10/11.
 */
public class GlideLoader implements ILoader {

    @Override
    public void load(Context context, ImageView target, Bitmap bitmap) {
        load(context, target, bitmap);
    }

    @Override
    public void load(Context context, ImageView target, Bitmap bitmap, LoaderOptions loaderOptions) {
        if (target == null) {
            return;
        }

        if (bitmap == null) {
            return;
        }

        RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(bitmap);

        /*
         * 包装参数、listener
         */
        wrap(null, requestBuilder, loaderOptions, null).into(target);
    }

    @Override
    public void load(Context context, ImageView target, int resId) {
        load(context, target, resId);
    }

    @SuppressLint("CheckResult")
    @Override
    public void load(Context context, ImageView target, int resId, LoaderOptions loaderOptions) {
        if (target == null) {
            return;
        }

        if (resId == 0) {
            return;
        }

        RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(resId);

        /*
         * 包装参数、listener
         */
        wrap(null, requestBuilder, loaderOptions, null).into(target);
    }

    @Override
    public void load(Context context, ImageView target, List<String> urlList) {
        load(context, target, urlList, null);
    }

    @Override
    public void load(Context context, ImageView target, List<String> urlList, LoaderOptions loaderOptions) {
        load(context, target, urlList, null, loaderOptions);
    }

    @Override
    public void load(Context context, ImageView target, List<String> urlList, String thumbnail, LoaderOptions loaderOptions) {
        load(context, target, urlList, thumbnail, loaderOptions, null);
    }

    @Override
    public void load(Context context, ImageView target, List<String> urlList, LoaderOptions loaderOptions, LoadListener loadListener) {
        load(context, target, urlList, null, loaderOptions, loadListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void load(Context context, ImageView target, List<String> urlList, String thumbnail, LoaderOptions loaderOptions, LoadListener loadListener) {
        if (target == null) {
            return;
        }

        if (urlList == null || urlList.size() == 0) {
            return;
        }

        RequestBuilder<Drawable> requestBuilder = getRequestBuilder(context, urlList, loaderOptions, loadListener);

        /*
         * 如果thumbnail 不空就填进去
         */
        if (!TextUtils.isEmpty(thumbnail)) {
            requestBuilder.thumbnail(Glide.with(context).load(thumbnail));
        }

        /*
         * 包装参数、listener
         */
        wrap(urlList.get(0), requestBuilder, loaderOptions, loadListener).into(target);
    }

    @Override
    public void load(Context context, ImageView target, String url) {
        load(context, target, url, null, null, null);
    }

    @Override
    public void load(Context context, ImageView target, String url, LoaderOptions loaderOptions) {
        load(context, target, url, null, loaderOptions, null);
    }

    @Override
    public void load(Context context, ImageView target, String url, String thumbnail, LoaderOptions loaderOptions) {
        load(context, target, url, thumbnail, loaderOptions, null);
    }

    @Override
    public void load(Context context, ImageView target, String url, LoaderOptions loaderOptions, LoadListener loadListener) {
        load(context, target, url, null, loaderOptions, loadListener);
    }

    /**
     * 其他的几个重载的方法都会直接调用这个方法
     *
     * @param url           url
     * @param thumbnail     缩略图
     * @param loaderOptions 参数
     * @param loadListener  监听器
     */
    @SuppressLint("CheckResult")
    @Override
    public void load(Context context, ImageView target, String url, String thumbnail, LoaderOptions loaderOptions, final LoadListener loadListener) {
        if (target == null) {
            throw new RuntimeException("GlideLoader : target must not null");
        }
        RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(url);

        /*
         * 如果thumbnail 不空就填进去
         */
        if (!TextUtils.isEmpty(thumbnail)) {
            requestBuilder.thumbnail(Glide.with(context).load(thumbnail));
        }

        /*
         * 包装参数、listener
         */
        wrap(url, requestBuilder, loaderOptions, loadListener).into(target);

    }

    private RequestBuilder<Drawable> getRequestBuilder(Context context, List<String> urlList, LoaderOptions loaderOptions, LoadListener loadListener) {
        int mCurrentPosition = 0;
        if (mCurrentPosition + 1 < urlList.size()) {
            RequestBuilder<Drawable> requestBuilder = getRequestBuilder(context, urlList.subList(1, urlList.size()), loaderOptions, loadListener);
            wrap(urlList.get(0), requestBuilder, loaderOptions, loadListener);
            return Glide.with(context).load(urlList.get(mCurrentPosition)).error(requestBuilder);
        } else {
            return Glide.with(context).load(urlList.get(0));
        }
    }

    @SuppressLint("CheckResult")
    private RequestBuilder<Drawable> wrap(String url, RequestBuilder<Drawable> requestBuilder, LoaderOptions loaderOptions, final LoadListener loadListener) {

        if (loaderOptions != null) {
            RequestOptions requestOptions = new RequestOptions();

            //设置圆
            if (loaderOptions.isCircle()) {
                requestOptions.transform(new GlideCircleTransform());
            }

            //跳过内存缓存和硬盘缓存
            if (loaderOptions.isSkipCache()) {
                requestOptions.skipMemoryCache(true);
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            }

            //设置圆角
            if (loaderOptions.getRoundRadius() != -1) {
                requestOptions.apply(RequestOptions.centerCropTransform()).transform(new GlideRoundTransform(loaderOptions.getRoundRadius()));
            }
            //设置error
            if (loaderOptions.getIconErrorRes() != -1) {
                requestOptions.error(loaderOptions.getIconErrorRes());
            }
            //设置placeholder
            if (loaderOptions.getIconLoadingRes() != -1) {
                requestOptions.placeholder(loaderOptions.getIconLoadingRes());
            }

            requestBuilder.apply(requestOptions);
        }

        if (loadListener != null) {
            requestBuilder.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    loadListener.onLoadFailed(e);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    loadListener.onLoadCompleted(resource);
                    return false;
                }
            });

            ProgressManager.addProgressListener(url, new OnProgressListener() {
                @Override
                public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                    loadListener.onLoadProgress((int) ((bytesRead * 1.0f / totalBytes) * 100.0f));

                    if (isDone) {
                        ProgressManager.removeProgressListener(imageUrl, this);
                    }
                }
            });
        }

        if (loaderOptions != null && loaderOptions.isTransition()) {
            requestBuilder.transition(withCrossFade());
        }

        return requestBuilder;
    }

    @Override
    public void loadAssets(ImageView target, String assetName) {

    }

    @Override
    public void loadFile(ImageView target, File file) {

    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


    private long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public void resume(Context context) {
    }

    @Override
    public void pause(Context context) {

    }
}
