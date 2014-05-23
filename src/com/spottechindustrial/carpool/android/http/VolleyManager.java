package com.spottechindustrial.carpool.android.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyManager {
    private static volatile RequestQueue queue;

    public static RequestQueue getQueueInstance(final Context context) {
        if (null == queue) {
            synchronized(VolleyManager.class) {
                if (null == queue) {
                    queue = Volley.newRequestQueue(context);
                }
            }
        }
        return queue;
    }
}
