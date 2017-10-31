package com.sherlockholmes.mvptest.model.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.sherlockholmes.mvptest.model.IDownloadModel;
import com.sherlockholmes.mvptest.presenter.IDownloadPresenter;
import com.sherlockholmes.mvptest.presenter.impl.DownloadPresenter;
import com.sherlockholmes.mvptest.util.HttpUtil;

/**
 * Created by SherlockHolmes on 2017/10/30.
 * <p>
 * ${PACKAGE_NAME}
 */

public class DownloadModel implements IDownloadModel {

	private MyHandler handler = new MyHandler();
	private HttpUtil httpUtil;
	private DownloadPresenter downloadPresenter;

	public DownloadModel(DownloadPresenter downloadPresenter) {
		this.httpUtil = new HttpUtil();
		this.downloadPresenter = downloadPresenter;
	}

	@Override
	public void downloadUrl(String url) {
		httpUtil.downloadImage(url, handler);
	}

	class MyHandler extends Handler {
		/**
		 * Subclasses must implement this to receive messages.
		 *
		 * @param msg
		 */
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 200:
					Bitmap bitmap = (Bitmap) msg.obj;
					downloadPresenter.downloadSuccess(bitmap);
					break;
				case 400:
					String result400 = (String) msg.obj;
					downloadPresenter.downloadFail(result400);
					break;
				case 500:
					String result500 = (String) msg.obj;
					downloadPresenter.downloadFail(result500);
					break;
				default:
					break;
			}
		}
	}
}
