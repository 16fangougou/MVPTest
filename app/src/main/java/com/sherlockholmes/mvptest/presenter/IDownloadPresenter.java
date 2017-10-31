package com.sherlockholmes.mvptest.presenter;

import android.graphics.Bitmap;

/**
 * Created by SherlockHolmes on 2017/10/30.
 *
 * ${PACKAGE_NAME}
 */

public interface IDownloadPresenter {

	void download(String url);

	void downloadSuccess(Bitmap result);

	void downloadFail(String result);

	void downloadProgress(int progress);
}
