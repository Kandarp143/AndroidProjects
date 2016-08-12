package com.example.k;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import com.shreejienterpriseinc.kalyanpushti.R;


import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ContactUs extends Activity {
	TextView txtwebsite, txtaddress, txtemailhaveli, txtPhone, tv, txt;
	ImageView getDirection;

	public double lat, lon;
	GPSTracker gps;

	String strAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);

		getDirection = (ImageView) findViewById(R.id.imgGetDirection);

		//strAddress = MyActivity.location;

		Geocoder coder = new Geocoder(this);

		try {
			List<Address> addressList = coder
					.getFromLocationName(strAddress, 5);

			if (addressList != null && addressList.size() > 0) {
				lat = (addressList.get(0).getLatitude());
				lon = (addressList.get(0).getLongitude());
			}
		} catch (Exception e) {
		}

		getDirection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// create class object
		        gps = new GPSTracker(ContactUs.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	String uri = "https://maps.google.com/maps?saddr="+ latitude + "," + longitude+"&daddr=22.302321,73.210442";
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
					intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
					startActivity(intent);
					
		        	
		        	// \n is for new line
		        	//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
		        
				
			}
		});

		txtwebsite = (TextView) findViewById(R.id.txtwebsite);
		txtPhone = (TextView) findViewById(R.id.txtphone);
		txtaddress = (TextView) findViewById(R.id.txtaddress);
		txt = (TextView) findViewById(R.id.txtview);
		txtemailhaveli = (TextView) findViewById(R.id.txtemailhaveli);

		// txtaddress.setText(MyActivity.location);

		tv = (TextView) findViewById(R.id.textView1);

		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		tv.setTypeface(font);
		txt.setTypeface(font);
		txtaddress.setTypeface(font);
		txtemailhaveli.setTypeface(font);
		txtPhone.setTypeface(font);
		txtwebsite.setTypeface(font);

		txtwebsite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), WebSite.class));
			}
		});


		txtemailhaveli.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL,
						new String[] { txtemailhaveli.getText().toString() });

				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email, "Send Email"));
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Main.class));
	}

	

}
