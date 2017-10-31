package com.sherlockholmes.mvptest.view;

import android.graphics.Bitmap;

/**
 * Created by SherlockHolmes on 2017/10/30.
 */

public interface IDownloadView {

	void showProgressView(boolean show);

	void setProgressProgress(int progress);

	void setView(Bitmap result);

	void showFailToast(String msg);
}
