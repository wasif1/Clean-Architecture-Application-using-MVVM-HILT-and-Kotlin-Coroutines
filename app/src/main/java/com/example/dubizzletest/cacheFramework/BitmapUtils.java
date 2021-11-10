package com.example.dubizzletest.cacheFramework;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by WASIF on 2021/11/10.
 * Three-tier Image Caching Framework
 */

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    static{
        netCacheUtils = new NetCacheUtils();
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    private static NetCacheUtils netCacheUtils;
    private static LocalCacheUtils localCacheUtils;
    private static MemoryCacheUtils memoryCacheUtils;

    //display picture
    public static void display(Context context, ImageView iv, String url){
        Bitmap bitmap = null;
        //Memory cache
        bitmap = memoryCacheUtils.readCache(url);
        if(bitmap != null){
            iv.setImageBitmap(bitmap);
            MyLogger.i(TAG,"Getting pictures from memory");
            return;
        }
        //Disk cache
        bitmap = localCacheUtils.readCache(context, url);
        if(bitmap != null){
            iv.setImageBitmap(bitmap);
            MyLogger.i(TAG,"Getting pictures from disk");
            return;
        }
        //Network cache
        netCacheUtils.getBitmapFromNet(context,iv,url);
    }

}