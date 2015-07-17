package com.tony.shigetongda;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SinglePageActivity extends Activity implements OnTouchListener {

	private static final String ENCODING = "UTF-8";

	private TextView contentTextView;
	private ImageView backButtonImageView;
	private ImageView bannerImageView;
	private RelativeLayout arrowLeftRelativeLayout;
	private RelativeLayout arrowRightRelativeLayout;
	private TextView arrowLeftTextView;
	private TextView arrowRightTextView;
	private ImageView arrowLeftImageView;
	private ImageView arrowRightImageView;
	private TextView activityTitleTextView;

	private int pageIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_page);
		initViews();
		pageIndex = getIntent().getExtras().getInt(Common.PAGE_INDEX);
		updateViewByIndex(pageIndex);
	}

	private void initViews() {
		contentTextView = (TextView) findViewById(R.id.content);
		contentTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

		backButtonImageView = (ImageView) findViewById(R.id.back);
		backButtonImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		bannerImageView = (ImageView) findViewById(R.id.banner);

		arrowLeftRelativeLayout = (RelativeLayout) findViewById(R.id.arrow_left);
		arrowLeftRelativeLayout.setOnTouchListener(this);

		arrowRightRelativeLayout = (RelativeLayout) findViewById(R.id.arrow_right);
		arrowRightRelativeLayout.setOnTouchListener(this);

		arrowLeftTextView = (TextView) findViewById(R.id.arrow_left_text);

		arrowRightTextView = (TextView) findViewById(R.id.arrow_right_text);

		arrowLeftImageView = (ImageView) findViewById(R.id.arrow_left_arrow);

		arrowRightImageView = (ImageView) findViewById(R.id.arrow_right_arrow);

		activityTitleTextView = (TextView) findViewById(R.id.activity_title);
	}

	private void updateViewByIndex(int pageIndex) {
		int bannerBackgroundResourceID = R.drawable.gongsijianjie_background;
		CharSequence content = "";
		// CharSequence arrowLeftText = "";
		// CharSequence arrowRightText = "";

		switch (pageIndex) {
		case Common.INDEX_GONG_SI_JIAN_JIE:
			bannerBackgroundResourceID = R.drawable.gongsijianjie_background;
			// content =
			content = getFromAssets("GongSiJianJie.txt");
			break;
		case Common.INDEX_ZHAO_SHANG_JIAN_JIE:
			bannerBackgroundResourceID = R.drawable.zhaoshangjianjie_background;
			content = getFromAssets("ZhaoShangJianJie.txt");
			break;
		case Common.INDEX_HE_ZUO_MO_SHI:
			bannerBackgroundResourceID = R.drawable.hezuomoshi_background;
			content = getFromAssets("HeZuoMoShi.txt");
			break;
		case Common.INDEX_HE_ZUO_ZHE_LI_RUN_DIAN:
			bannerBackgroundResourceID = R.drawable.hezuozhelirundian_background;
			content = getFromAssets("HeZuoZheLiRunDian.txt");
			break;
		case Common.INDEX_YE_PAN_XIANG_MU:
			bannerBackgroundResourceID = R.drawable.yepan_background;
			content = getFromAssets("YePan.txt");
			break;
		case Common.INDEX_WO_YAO_JIA_MENG:
			bannerBackgroundResourceID = R.drawable.woyaojiameng_background;
			content = getFromAssets("WoYaoJiaMeng.txt");
			break;
		default:
			break;
		}

		CharSequence activityTitle = Common.TEXT[pageIndex];
		CharSequence arrowLeftText = pageIndex == 0 ? "" : Common.TEXT[pageIndex - 1];
		CharSequence arrowRightText = pageIndex == Common.TEXT.length - 1 ? ""
				: Common.TEXT[pageIndex + 1];

		bannerImageView.setImageResource(bannerBackgroundResourceID);
		contentTextView.setText(content);
		contentTextView.setScrollX(0);
		contentTextView.setScrollY(0);
		if (arrowLeftText.equals("")) {
			arrowLeftRelativeLayout.setVisibility(View.INVISIBLE);
		} else {
			arrowLeftRelativeLayout.setVisibility(View.VISIBLE);
			arrowLeftTextView.setText(arrowLeftText);
		}
		if (arrowRightText.equals("")) {
			arrowRightRelativeLayout.setVisibility(View.INVISIBLE);
		} else {
			arrowRightRelativeLayout.setVisibility(View.VISIBLE);
			arrowRightTextView.setText(arrowRightText);
		}
		activityTitleTextView.setText(activityTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("tony", "SinglePageActivity onTouch v = " + v + ", event = " + event.getAction());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (v == arrowLeftRelativeLayout) {
				arrowLeftImageView.getDrawable().setAlpha(100);
			} else if (v == arrowRightRelativeLayout) {
				arrowRightImageView.getDrawable().setAlpha(100);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (v == arrowLeftRelativeLayout) {
				arrowLeftImageView.getDrawable().setAlpha(255);
				updateViewByIndex(pageIndex - 1);
				pageIndex--;
			} else if (v == arrowRightRelativeLayout) {
				arrowRightImageView.getDrawable().setAlpha(255);
				updateViewByIndex(pageIndex + 1);
				pageIndex++;
			}
			break;
		default:
			break;
		}
		return true;
	}

	// 从assets 文件夹中获取文件并读取数据
	public String getFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
