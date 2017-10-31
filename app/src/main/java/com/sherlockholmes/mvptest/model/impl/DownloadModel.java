package com.sherlockholmes.mvptest.model.impl;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.sherlockholmes.mvptest.model.IDownloadModel;
import com.sherlockholmes.mvptest.presenter.impl.DownloadPresenter;
import com.sherlockholmes.mvptest.util.HttpUtil;

/**
 * Created by SherlockHolmes on 2017/10/30.
 * <p>
 * ${PACKAGE_NAME}
 */

public class DownloadModel implements IDownloadModel {

	private static MyHandler handler = new MyHandler();
	private HttpUtil httpUtil;
	private static DownloadPresenter downloadPresenter;

	public DownloadModel(DownloadPresenter downloadPresenter) {
		this.httpUtil = new HttpUtil();
		this.downloadPresenter = downloadPresenter;
	}

	@Override
	public void downloadUrl(String url) {
		httpUtil.downloadImage(url, handler);
	}

	static class MyHandler extends Handler {
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
				default:
					String result = (String) msg.obj;
					downloadPresenter.downloadFail(result);
					break;
			}
		}
	}
}
