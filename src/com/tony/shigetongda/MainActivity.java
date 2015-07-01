package com.tony.shigetongda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnTouchListener {

	private RelativeLayout WoYaoJiaMengLayout;
	private RelativeLayout activityMainLayout;
	private RelativeLayout GongSiJianJieLayout;
	private RelativeLayout ZhaoShangJianJieLayout;
	private RelativeLayout HeZuoMoShiLayout;
	private RelativeLayout HeZuoZheLiRunDianLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		resizeEverything();
	}

	private void initView() {
		activityMainLayout = (RelativeLayout) findViewById(R.id.activity_main);

		WoYaoJiaMengLayout = (RelativeLayout) findViewById(R.id.wo_yao_jia_meng);
		WoYaoJiaMengLayout.setOnTouchListener(this);

		GongSiJianJieLayout = (RelativeLayout) findViewById(R.id.gong_si_jian_jie);
		GongSiJianJieLayout.setOnTouchListener(this);

		ZhaoShangJianJieLayout = (RelativeLayout) findViewById(R.id.zhao_shang_jian_jie);
		ZhaoShangJianJieLayout.setOnTouchListener(this);

		HeZuoMoShiLayout = (RelativeLayout) findViewById(R.id.he_zuo_mo_shi);
		HeZuoMoShiLayout.setOnTouchListener(this);

		HeZuoZheLiRunDianLayout = (RelativeLayout) findViewById(R.id.he_zuo_zhe_li_run_dian);
		HeZuoZheLiRunDianLayout.setOnTouchListener(this);

	}

	private void resizeEverything() {
		Display display = this.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		RelativeLayout.LayoutParams lp = (LayoutParams) WoYaoJiaMengLayout.getLayoutParams();

		lp.height = (height - 110) / 3;
		activityMainLayout.updateViewLayout(WoYaoJiaMengLayout, lp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("tony", "MainActivity onTouch v = " + v + ", event = " + event.getAction());
		Drawable background = v.getBackground();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			background.setAlpha(100);
			break;
		case MotionEvent.ACTION_UP:
			background.setAlpha(255);
			Intent intent = new Intent();
			int pageIndex = Common.INDEX_GONG_SI_JIAN_JIE;
			if (v == GongSiJianJieLayout) {
				pageIndex = Common.INDEX_GONG_SI_JIAN_JIE;
			} else if (v == ZhaoShangJianJieLayout) {
				pageIndex = Common.INDEX_ZHAO_SHANG_JIAN_JIE;
			} else if (v == HeZuoMoShiLayout) {
				pageIndex = Common.INDEX_HE_ZUO_MO_SHI;
			} else if (v == HeZuoZheLiRunDianLayout) {
				pageIndex = Common.INDEX_HE_ZUO_ZHE_LI_RUN_DIAN;
			} else if (v == WoYaoJiaMengLayout) {
				pageIndex = Common.INDEX_WO_YAO_JIA_MENG;
			}
			intent.setClass(MainActivity.this, SinglePageActivity.class);
			intent.putExtra(Common.PAGE_INDEX, pageIndex);
			startActivity(intent);
			break;

		default:
			break;
		}
		return true;
	}
}
