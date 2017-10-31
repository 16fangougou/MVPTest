package com.sherlockholmes.mvptest.presenter.impl;

import android.graphics.Bitmap;

import com.sherlockholmes.mvptest.model.impl.DownloadModel;
import com.sherlockholmes.mvptest.presenter.IDownloadPresenter;
import com.sherlockholmes.mvptest.view.IDownloadView;

/**
 * Created by SherlockHolmes on 2017/10/30.
 *
 * ${PACKAGE_NAME}
 */

public class DownloadPresenter implements IDownloadPresenter {

	private IDownloadView iDownloadView;
	private DownloadModel downloadModel;

	public DownloadPresenter(IDownloadView iDownloadView) {
		this.iDownloadView = iDownloadView;
		this.downloadModel = new DownloadModel(this);
	}

	@Override
	public void download(String url) {
		iDownloadView.showProgressView(true);
		downloadModel.downloadUrl(url);
	}

	@Override
	public void downloadSuccess(Bitmap result) {
		iDownloadView.showProgressView(false);
		iDownloadView.setView(result);
	}

	@Override
	public void downloadFail(String result) {
		iDownloadView.showProgressView(false);
		iDownloadView.showFailToast(result);
	}

	@Override
	public void downloadProgress(int progress) {
		iDownloadView.setProgressProgress(progress);
	}
}
