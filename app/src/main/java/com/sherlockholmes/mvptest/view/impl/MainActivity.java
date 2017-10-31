package com.sherlockholmes.mvptest.view.impl;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sherlockholmes.mvptest.AppConstant;
import com.sherlockholmes.mvptest.R;
import com.sherlockholmes.mvptest.presenter.impl.DownloadPresenter;
import com.sherlockholmes.mvptest.view.IDownloadView;

/**
 * 最后再看一下View接口的具体实现，也就是Activity的实现：
 */
public class MainActivity extends AppCompatActivity implements IDownloadView {

	private ProgressBar progressBar;
	private Button button;
	private TextView textView;
	private ImageView imageView;
	private DownloadPresenter downloadPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		/**
		 * 我们在Activity中初始化Presenter中传递的this，也就是当前Activity（View）
		 */
		downloadPresenter = new DownloadPresenter(this);

		button = findViewById(R.id.btn_start);
		progressBar = findViewById(R.id.progress_bar);
		textView = findViewById(R.id.tv_result);
		imageView = findViewById(R.id.image);

		/**
		 * 在点下按钮执行开始下载任务的时候，View（Activity）中没有具体的实现，只是调用了Presenter中的download方法，
		 * 而Presenter中的download又会去调用Model的download方法，
		 * Model又会在根据具体逻辑（在这里就是Http请求）的状态去调用Presenter中的方法。
		 */
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				downloadPresenter.download(AppConstant.IMAGE);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		imageView.setImageBitmap(null);
	}

	@Override
	public void setProgressProgress(int progress) {
		progressBar.setProgress(progress);
	}

	@Override
	public void showProgressView(boolean show) {
		if (show) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void setView(Bitmap result) {
		imageView.setImageBitmap(result);
	}

	@Override
	public void showFailToast(String msg) {
		textView.setText(msg);
	}

	/**
	 * 至此，我们就通过MVP 的模式实现了我们之前所设想的Activity：
	 * ①：Button的click方法负责发起下载任务，但又不负责具体实现，而是由Presenter转接给Model去实现
	 * ②：Activity 什么时候显示ProgressDialog，什么时候显示Toast直接由Presenter告诉他，他只做一个View想做的事情
	 * ③：Activity里没有任何逻辑处理，所有的逻辑判断都在Model中完成了。
	 */
}