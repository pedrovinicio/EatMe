package example.eatme.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import example.eatme.R;


public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.setContentView(R.layout.splash_activity_layout);
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent i = new Intent(getBaseContext(),ListFoodActivity.class);
				startActivity(i);
				SplashActivity.this.finish();
			}
		};

		Timer splashTimer = new Timer();
		splashTimer.schedule(task,3000);
	}
}
