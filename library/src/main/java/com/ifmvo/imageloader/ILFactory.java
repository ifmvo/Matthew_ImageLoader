package com.ifmvo.imageloader;

/**
 * Created by wanglei on 2016/11/28.
 */
public class ILFactory {

    private static ILoader loader;

    public static ILoader getLoader() {
            synchronized (ILFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }

        return loader;
    }


}
