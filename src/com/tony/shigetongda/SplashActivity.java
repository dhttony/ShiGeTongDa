package com.tony.shigetongda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		// �ӳټ��룬Ȼ����ת����¼ҳ��
		new Handler().postDelayed(r, 3000);
	}

	Runnable r = new Runnable() {
		@Override
		public void run() {
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
}
