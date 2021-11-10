package com.example.dubizzletest.cacheFramework;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by WASIF on 2021/11/10.
 * Memory caching: data storage through HashMap
 */
public class MemoryCacheUtils {
    static {
        //caches = new HashMap<>();
        //The memory of Android virtual machine is only 16M, which is prone to OOM exception (memory overflow).
        //The java language provides another mechanism: soft reference, weak reference, virtual reference
        //Soft References: Recycle Soft Referenced Objects when Virtual Machines are out of memory
        //Weak references: Recycle objects immediately when they are not applied
        //Virtual Reference: Recycling is possible under any circumstances
        //The default data type in java is the strongly referenced type
        //caches = new HashMap<>();

        //Because starting with Android 2.3 (API Level 9),
        //Garbage collectors tend to recycle objects with soft or weak references.
        //This makes soft and weak references unreliable.
        //google officially recommends that we use such a caching mechanism: LruCache.
        //LruCache lru:least recently used algorithm
        //A
        //B
        //C (Recent Minimum Priority Recycled)
        //B
        //A
        long maxMemory = Runtime.getRuntime().maxMemory();//Get the maximum memory size of the Dalvik virtual machine: 16
    // 2. Specify the size of the memory cache collection
    lruCache = new LruCache<String,Bitmap>((int) (maxMemory/8)){ 
            //3. Get the size of each picture
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    // 1. Create LruCache objects
    private static LruCache<String, Bitmap> lruCache;



    //private static HashMap<String, SoftReference<Bitmap>> caches;
    //private static HashMap<String, Bitmap> caches;

    //Write cache
    public static void saveCache(Bitmap bitmap,String url){
    //caches.put(url,bitmap);
    //SoftReference<Bitmap> soft = new SoftReference<Bitmap>(bitmap);
    //caches.put(url,soft);
    // 4. Caching objects using put() method
        lruCache.put(url,bitmap);
    }

    //Read cache
    public static Bitmap readCache(String url){
        //Bitmap bitmap = caches.get(url);
    //SoftReference<Bitmap> soft = caches.get(url);
    //if(soft != null){
    //Bitmap bitmap = soft.get();
    //return bitmap;
    //}
    // 5. Use get() method to extract objects
        return lruCache.get(url);
    }
}