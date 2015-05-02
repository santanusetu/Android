package com.faiz.drinkndrive;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;

public class MySplash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.splash);
		Thread splash;
		splash = new Thread() {
			public void run() {
				super.run();

				try {
					sleep(1300);

				} catch (InterruptedException e) {

				} finally {
					finish();
					startActivity(new Intent(MySplash.this, MainActivity.class));

				}

			}
		};
		splash.start();

	}

}
