package com.example.dubizzletest.cacheFramework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  Created by WASIF on 2021/11/10.
 *  Three-tier Cache: Network Cache
 */

public class NetCacheUtils {
    private static final String TAG = "NetCacheUtils";
    private Context context;
    //Getting pictures from the Internet
    public void getBitmapFromNet(Context context, ImageView iv, String url){
        this.context = context;
        //Connect ImageView with url: Solve the bug of image misalignment
        iv.setTag(url);

        // Create an internal class BitmapTask to load network images asynchronously
        new BitmapTask().execute(iv,url);
    }

    //Asynchronous task
    class BitmapTask extends AsyncTask<Object,Void,Bitmap> {
    // Define containers and network links for images
        private ImageView iv;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... params) {
            //Get parameters
            iv = (ImageView) params[0];
            url = (String) params[1];

            //Download Bitmap ()
            Bitmap bitmap = downloadBitmap(url);
            // MyLogger is a custom logging tool class (attachments will be provided)
            MyLogger.i(TAG,"Loading pictures from the network");

            //Cache loaded network pictures into disk and memory:
            // Cache to disk first (implemented after LocalCacheUtils)
            LocalCacheUtils.saveCache(context,bitmap,url);
            // Re-caching to memory (implemented later in Memory CacheUtils)
            MemoryCacheUtils.saveCache(bitmap,url);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //Get the url corresponding to ImageView
            String url = (String) iv.getTag();
            // Only when the same picture is displayed to prevent image misalignment
            if(bitmap != null && this.url.equals(url)){
                iv.setImageBitmap(bitmap);
            }
        }
    }

    //Method of downloading pictures (using IO streams)
    private Bitmap downloadBitmap(String url) {
        Bitmap bitmap = null;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(6000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream inputStream = conn.getInputStream();
                //Converting streams into Bitmap objects
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return bitmap;
    }

}