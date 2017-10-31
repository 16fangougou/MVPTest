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
		downloadPresenter = new DownloadPresenter(this);

		button = findViewById(R.id.btn_start);
		progressBar = findViewById(R.id.progress_bar);
		textView = findViewById(R.id.tv_result);
		imageView = findViewById(R.id.image);

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
}
