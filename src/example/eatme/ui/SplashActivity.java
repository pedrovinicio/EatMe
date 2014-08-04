package example.eatme.ui;

import example.eatme.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.splash_activity_layout);

		Thread background = new Thread() {
			public void run() {
				try {
					sleep(3*1000);
				} catch (Exception e) {
				}

				Intent i = new Intent(getBaseContext(),ListFoodActivity.class);
				startActivity(i);

				SplashActivity.this.finish();
			}
		};

		background.start();
	}
}
