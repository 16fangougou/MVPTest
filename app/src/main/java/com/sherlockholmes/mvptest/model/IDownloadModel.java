package com.sherlockholmes.mvptest.model;

/**
 * Created by SherlockHolmes on 2017/10/30.15:33
 *
 * Model 接口定义所有需要实现的业务逻辑，在我们的下载任务中，业务逻辑只有一个，就是下载
 */

public interface IDownloadModel {

	/**
	 * 定义下载接口
	 * @param url
	 */
	void downloadUrl(String url);
}
