package com.ifmvo.imageloader.progress;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 陈序员 on 2017/10/9.
 */
public class ProgressManager {

    private static Map<String, WeakReference<OnProgressListener>> listenersMap = Collections.synchronizedMap(new HashMap<String, WeakReference<OnProgressListener>>());
    private static OkHttpClient okHttpClient;

    private ProgressManager() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request request = chain.request();
                            Response response = chain.proceed(request);
                            WeakReference<OnProgressListener> onProgressListenerWeakReference = listenersMap.get(request.url().toString());
                            if (listenersMap.size() > 0 && onProgressListenerWeakReference != null){
                                return response.newBuilder()
                                        .body(new ProgressResponseBody(request.url().toString(), response.body(), onProgressListenerWeakReference.get()))
                                        .build();
                            }
                            return response;
                        }
                    })
                    .build();
        }
        return okHttpClient;
    }

//    private static final OnProgressListener LISTENER = new OnProgressListener() {
//        @Override
//        public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
//            if (listenersMap == null || listenersMap.size() == 0) return;
//            for (int i = 0; i < listenersMap.size(); i++) {
//                WeakReference<OnProgressListener> listener = listenersMap.get(i);
//                OnProgressListener progressListener = listener.get();
//                if (progressListener == null) {
//                    listenersMap.remove(i);
//                } else {
//                    progressListener.onProgress(imageUrl, bytesRead, totalBytes, isDone, exception);
//                }
//            }
//        }
//    };

    /**
     * 使用URL做key 保存在map中
     * @param key
     * @param progressListener
     */
    public static void addProgressListener(String key, OnProgressListener progressListener) {
        if (progressListener == null || TextUtils.isEmpty(key)) return;

        if (!listenersMap.containsKey(key)) {
            listenersMap.put(key, new WeakReference<>(progressListener));
        }
    }

    public static void removeProgressListener(String key, OnProgressListener progressListener) {
        if (progressListener == null || TextUtils.isEmpty(key)) return;
        listenersMap.remove(key);
    }

//    private static WeakReference<OnProgressListener> findProgressListener(OnProgressListener listener) {
//        if (listener == null) return null;
//        if (listenersMap == null || listenersMap.size() == 0) return null;
//
//        for (int i = 0; i < listenersMap.size(); i++) {
//            WeakReference<OnProgressListener> progressListener = listenersMap.get(i);
//            if (progressListener.get() == listener) {
//                return progressListener;
//            }
//        }
//        return null;
//    }


}
