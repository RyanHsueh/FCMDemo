package com.example.ryanhsueh.fcmdemo;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ryanhsueh on 2018/8/22
 */
public class NotificationCenter {

    private static volatile NotificationCenter sInstance;

    private Set<OnFCMReceivedListener> mFCMListeners =
            Collections.newSetFromMap(new ConcurrentHashMap<OnFCMReceivedListener, Boolean>());

    public interface OnFCMReceivedListener {
        void onMessageReceived(String message);
    }

    public void registerFCMReceivedListener(OnFCMReceivedListener listener) {
        mFCMListeners.add(listener);
    }
    public void unregisterFCMReceivedListener(OnFCMReceivedListener listener) {
        mFCMListeners.remove(listener);
    }

    public static NotificationCenter getInstance() {
        if (sInstance == null) {
            synchronized (NotificationCenter.class) {
                if (sInstance == null) {
                    sInstance = new NotificationCenter();
                }
            }
        }

        return sInstance;
    }

    private NotificationCenter() {

    }

    public void notifyMessage(String message) {
        for (OnFCMReceivedListener listener : mFCMListeners) {
            listener.onMessageReceived(message);
        }
    }

}
