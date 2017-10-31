package com.sherlockholmes.mvptest.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SherlockHolmes on 2017/10/30.16:46
 */

public class HttpUtil {

	private static String TAG = "HttpUtil";
	private Bitmap bitmap;
	private InputStream inputStream;

	public void downloadImage(final String imageUrl, final Handler handler) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message message = handler.obtainMessage();
				try {
					URL url = new URL(imageUrl);
					HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
					httpURLConnection.setDoInput(true);
					httpURLConnection.connect();
					int code = httpURLConnection.getResponseCode();
					Log.d(TAG, "run code:" + code);

					if (code == HttpURLConnection.HTTP_OK) {
						// 将得到的数据转化成InputStream
						inputStream = httpURLConnection.getInputStream();
						// 将InputStream转换成Bitmap
						bitmap = BitmapFactory.decodeStream(inputStream);
						if (bitmap != null) {
							Log.d(TAG, "run: bitmap != null");
						}
						message.what = 200;
						message.obj = bitmap;
					} else {
						message.what = code;
						message.obj = httpURLConnection.getResponseMessage();
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					message.what = 400;
					message.obj = e.toString();
				} finally {
					if (null != inputStream) {
						try {
							inputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				handler.sendMessage(message);
			}
		}).start();
	}
}
