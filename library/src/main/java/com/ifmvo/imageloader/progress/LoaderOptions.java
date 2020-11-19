package com.ifmvo.imageloader.progress;


import com.ifmvo.imageloader.R;

/**
 * Created by 陈序员 on 2017/10/10.
 */

public class LoaderOptions {

    private int iconErrorRes = -1;

    private int iconLoadingRes = -1;

    private boolean isCircle = false;

    private int roundRadius = -1;

    private boolean skipCache = false;

    private boolean transition = false;

    private Object signatureObject = null;

    public LoaderOptions error(int iconRes) {
        this.iconErrorRes = iconRes;
        return this;
    }

    public LoaderOptions placeHolder(int iconRes) {
        this.iconLoadingRes = iconRes;
        return this;
    }

    public LoaderOptions circle() {
        this.isCircle = true;
        return this;
    }

    public LoaderOptions round(int dpRoundRadius) {
        this.roundRadius = dpRoundRadius;
        return this;
    }

    public LoaderOptions defaultOptions() {
        this.iconErrorRes = R.drawable.ic_error;
        this.iconLoadingRes = R.drawable.ic_loading;
        return this;
    }

    public LoaderOptions skipCache() {
        this.skipCache = true;
        return this;
    }

    public LoaderOptions transition() {
        this.transition = true;
        return this;
    }

    public LoaderOptions signatureObject(Object object) {
        signatureObject = object;
        return this;
    }

    public boolean isTransition() {
        return transition;
    }

    public boolean isSkipCache() {
        return skipCache;
    }

    public final int getIconErrorRes() {
        return iconErrorRes;
    }

    public final int getIconLoadingRes() {
        return iconLoadingRes;
    }

    public final boolean isCircle() {
        return isCircle;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public Object getSignatureObject() {
        return signatureObject;
    }
}
