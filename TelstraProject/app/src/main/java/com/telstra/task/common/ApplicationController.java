package com.telstra.task.common;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ruban on 1/22/2016.
 * The best way to maintain volley core objects and request queue is, making them global by creating a singleton class which extends Application object.
 *
 */
public class ApplicationController extends Application {
    public static final String TAG = ApplicationController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static ApplicationController mInstance;

    /**
     *   This method will be called only once when the application started
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    /**
     *   This method will return RequestQueue Object
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     *  Initialising Image Loader by passing Object of RequestQueue and LruBitmap Cache
     */
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    /**
     *   Add the request to the RequestQueue with tag.
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * Add the request to the RequestQueue.
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Cancel pending request using tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    /**
     * Checks for Internet connection
     */
    public static boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) mInstance.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }


    /**
     * Show Toast alert to user
     *
     */
    public static void displayToast(String msg){
        Toast.makeText(mInstance.getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}
