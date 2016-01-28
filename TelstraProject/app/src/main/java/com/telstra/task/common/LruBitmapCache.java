package com.telstra.task.common;

/**
 * Created by Ruban on 1/22/2016.
 * This class is required to handle image cache.
 */

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruBitmapCache extends LruCache<String, Bitmap> implements
        ImageCache {

    /**
     *  Allocating Cache size based on Device Memory
     */
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }


    /**
     *  Allocating Cache size
     */
    public LruBitmapCache() {

        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int sizeInKiloBytes) {

        super(sizeInKiloBytes);
    }

    /**
     * Returns the size of the entry for {@code key} and {@code value} in
     * user-defined units.
     */
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }


    /**
     *   Get Bitmap by passing image url
     */
    @Override
    public Bitmap getBitmap(String url) {

        return get(url);
    }
    /**
     *   put the bitmap by using url as tag and reference
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}