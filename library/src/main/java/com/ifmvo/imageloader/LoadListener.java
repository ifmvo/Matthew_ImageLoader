package com.ifmvo.imageloader;

import android.graphics.drawable.Drawable;

/**
 * Created by wanglei on 2016/12/21.
 */

public abstract class LoadListener {

    public boolean onLoadFailed(Throwable e) {
        return false;
    }

    public abstract boolean onLoadCompleted(Drawable drawable);

    public void onLoadProgress(int progress){}
}
