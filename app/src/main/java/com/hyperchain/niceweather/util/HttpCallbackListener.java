package com.hyperchain.niceweather.util;

/**
 * Created by Newcon on 2016/11/28.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
