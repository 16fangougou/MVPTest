package com.sherlockholmes.mvptest.presenter;

import android.graphics.Bitmap;

/**
 * Created by SherlockHolmes on 2017/10/30.
 *
 * Presenter 接口作为连接Model和View的中间桥梁，需要将二者连接起来，因此他需要完成以下工作：
 *
 * 执行下载任务；
 * 下载成功返回下载结果；
 * 下载过程返回下载进度；
 * 下载失败回调
 */

public interface IDownloadPresenter {

	void download(String url);

	void downloadSuccess(Bitmap result);

	void downloadFail(String result);

	void downloadProgress(int progress);
}
