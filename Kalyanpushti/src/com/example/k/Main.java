package com.example.k;

import com.example.k.Main;
import com.shreejienterpriseinc.kalyanpushti.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends Activity {
	TextView tv;

	ImageView darshan, darshanTime, pushtimarg, utsav, activities, contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tv = (TextView) findViewById(R.id.textView1);
		
		final ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		tv.setTypeface(font);

		darshan = (ImageView) findViewById(R.id.imageView2);
		darshanTime = (ImageView) findViewById(R.id.imageView3);
		pushtimarg = (ImageView) findViewById(R.id.imageView4);
		utsav = (ImageView) findViewById(R.id.imageView5);
		activities = (ImageView) findViewById(R.id.imageView6);
		contact = (ImageView) findViewById(R.id.imageView7);
		
	

		darshan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), Darshan.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);

			}
		});
		darshanTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),Darshantime.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
			}
		});
		pushtimarg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),Pushtimarg.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
			}
		});

		utsav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED))
			    {
						finish();
						Intent intent = new Intent(getApplicationContext(),Utsav.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
			    }
				else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED )
				    {
				    	//finish();
				        //Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
				        //startActivity(new Intent(getApplicationContext(),Main.class));
				        
				        AlertDialog alertDialog = new AlertDialog.Builder(Main.this).create();
				        
				     // Setting Dialog Title
			            alertDialog.setTitle("Connection");

			            // Setting Dialog Message
			            alertDialog.setMessage("You must be connected to the internet");

			            // Setting Icon to Dialog
			            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			            

			            // Setting OK Button
			            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

			                        public void onClick(DialogInterface dialog,int which) 
			                        {
			                            // Write your code here to execute after dialog closed
			                        //Toast.makeText(getApplicationContext(),"You clicked on OK", Toast.LENGTH_SHORT).show();
			                        	startActivity(new Intent(getApplicationContext(),Main.class));
			                        }
			                    });

			            // Showing Alert Message
			            alertDialog.show();


				    }
			}
		});
		activities.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),Activities.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
			}
		});

		contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(),ContactUs.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Exit Application")
				.setMessage("Are you sure you want to close the application.?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// Toast.makeText(getApplicationContext(),
								// "this is my Toast message!!! =)",
								// Toast.LENGTH_LONG).show();
								finish();
								System.exit(0);
								//mp.stop();
							}
						}).setNegativeButton("No", null).show();
	}
}
