package com.example.lanschooling;

import com.example.lanschooling.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends Activity {

	    // Splash screen timer
		private static int SPLASH_TIME_OUT = 1000;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_splash_screen);

			final ImageView splashImageView = (ImageView) findViewById(R.id.imageView1);
			splashImageView.setBackgroundResource(R.anim.flag);
			final AnimationDrawable frameAnimation = (AnimationDrawable) splashImageView
					.getBackground();

			splashImageView.post(new Runnable() {
				@Override
				public void run() {
					frameAnimation.start();
				}
			});

			new Handler().postDelayed(new Runnable() {

				/*
				 * Showing splash screen with a timer. This will be useful when you
				 * want to show case your app logo / company
				 */

				@Override
				public void run() {
					
					// This method will be executed once the timer is over
					// Start your app main activity
					Intent i = new Intent(SplashScreen.this,ConnectionActivity.class);
					startActivity(i);

					// close this activity
					finish();
				}
			}, SPLASH_TIME_OUT);
		}

	}
