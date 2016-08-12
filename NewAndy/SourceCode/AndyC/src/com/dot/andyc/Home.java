package com.dot.andyc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Home extends Activity {

	// define java button
	Button btn_wmp, btn_power, btn_ppt, btn_mouse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// set no title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.home);

		// get button from layout
		btn_wmp = (Button) findViewById(R.id.wmp);
		btn_power = (Button) findViewById(R.id.power);
		btn_ppt = (Button) findViewById(R.id.ppt);
		btn_mouse = (Button) findViewById(R.id.mouse);

		// set on click listner
		btn_wmp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(Home.this, Wmp.class));

					}
				});

			}
		});

		btn_mouse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(Home.this, Mouse.class));

					}
				});

			}
		});

		btn_ppt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(Home.this, Ppt.class));

					}
				});

			}
		});

		btn_power.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(Home.this, Power.class));

					}
				});

			}
		});

		btn_wmp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						startActivity(new Intent(Home.this, Wmp.class));

					}
				});

			}
		});

	}

	// close app
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
	}

	// back pressed validation
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		new AlertDialog.Builder(this)
				.setTitle("Are you sure?")
				.setMessage("Are you sure want to exit?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// dos.writeUTF("exit");
								finish();

							}
						}).setNegativeButton("No", null).show();

	}

}
