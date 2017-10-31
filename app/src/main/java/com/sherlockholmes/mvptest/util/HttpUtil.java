package com.sherlockholmes.mvptest.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
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
					httpURLConnection.setDoOutput(false);
					httpURLConnection.setRequestMethod("GET");
					httpURLConnection.setConnectTimeout(5000);
					httpURLConnection.setReadTimeout(10000);
					httpURLConnection.setUseCaches(true);
					httpURLConnection.connect();
					int code = httpURLConnection.getResponseCode();
					Log.d(TAG, "run code:" + code);

					inputStream = httpURLConnection.getInputStream();
					bitmap = BitmapFactory.decodeStream(inputStream);
					byte[] bytes = getBytesInputStream(inputStream);
					bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
					if (bitmap != null) {
						Log.d(TAG, "run: bitmap != null");
					}
					message.what = 200;
					message.obj = bitmap;
				} catch (MalformedURLException e) {
					e.printStackTrace();
					message.what = 500;
					message.obj = e.toString();
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
					if (null != bitmap) {
						try {
							bitmap.recycle();
							bitmap = null;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				handler.sendMessage(message);
			}
		}).start();
	}

	private byte[] getBytesInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[512];
		int len;
		while ((len = is.read(buff)) != -1) {
			arrayOutputStream.write(buff, 0, len);
		}
		is.close();
		arrayOutputStream.close();
		return arrayOutputStream.toByteArray();
	}
}
