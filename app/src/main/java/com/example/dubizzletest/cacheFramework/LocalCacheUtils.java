package com.example.dubizzletest.cacheFramework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by WASIF on 2021/11/10.
 * Disk cache (Local_cache directory where data is stored in the cache directory of the application)
 */
public class LocalCacheUtils {
    //Write cache
    public static void saveCache(Context context, Bitmap bitmap, String url){
        //Cache directory
        File dir = new File(context.getCacheDir(),"local_cache");
        if(!dir.exists()){
            dir.mkdirs();
        }

        //Cache pictures in the cache directory
        // Md5Utils here is a tool class for generating md5 files (the attachment will provide)
        File file = new File(dir, Md5Utils.encode(url));
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Core code
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
    }

    //Read cache
    public static Bitmap readCache(Context context,String url){
        File dir = new File(context.getCacheDir(),"local_cache");
        if(!dir.exists()){
            return null;
        }
        File file = new File(dir, Md5Utils.encode(url));
        if(!file.exists()){
            return null;
        }
        // Core code
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        //Important operation: Caching data in memory
        // Memory CacheUtils talks about it right away
        MemoryCacheUtils.saveCache(bitmap,url);
        return bitmap;
    }

}