package com.sherlockholmes.mvptest.view;

import android.graphics.Bitmap;

/**
 * Created by SherlockHolmes on 2017/10/30.
 *
 * View 接口定义所有需要实现的视图逻辑，在我们的下载任务中，视图逻辑包括：
 *
 * 显示ProgressDialog；
 * 显示Dialog具体进度；
 * 显示具体的View（设置图片）
 * 显示错误信息（Toast提示）
 */

public interface IDownloadView {

	void showProgressView(boolean show);

	void setProgressProgress(int progress);

	void setView(Bitmap result);

	void showFailToast(String msg);
}
