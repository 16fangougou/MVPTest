package com.sherlockholmes.mvptest.presenter.impl;

import android.graphics.Bitmap;

import com.sherlockholmes.mvptest.model.impl.DownloadModel;
import com.sherlockholmes.mvptest.presenter.IDownloadPresenter;
import com.sherlockholmes.mvptest.view.IDownloadView;

/**
 * Created by SherlockHolmes on 2017/10/30.
 *
 * 可以看到，我们在DownloadPresenter的构造方法中，同时实例化了Model和View，这样Presenter中就同时包含了两者；
 * 这样；在Presenter具体实现中，业务相关的操作由Model去完成（例如download），视图相关的操作由View去完成。
 * Presenter 作为桥梁的作用就这样体现出来了，巧妙的将View和Model的具体实现连接了起来。
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
