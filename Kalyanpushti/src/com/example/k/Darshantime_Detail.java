package com.example.k;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Darshantime_Detail extends Activity {
	TextView header, address1, address2,time1, time2, time3, time4,
			time5, time6, type, txtemail,txtphone,txtwebsite,txtadd,txtdt;
	ImageView getDirection; 

	public static double lat;
	public static double lon;

	String strAddress;
	GPSTracker gps;

	String str, name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.darshantime_detail);

		Intent i = getIntent();
		name = i.getStringExtra("Header");
		str = i.getStringExtra("Type");

		time1 = (TextView) findViewById(R.id.txttime1);
		time2 = (TextView) findViewById(R.id.txttime2);
		time3 = (TextView) findViewById(R.id.txttime3);
		time4 = (TextView) findViewById(R.id.txttime4);
		time5 = (TextView) findViewById(R.id.txttime5);
		time6 = (TextView) findViewById(R.id.txttime6);
		txtemail = (TextView) findViewById(R.id.txtemail);
		txtphone=  (TextView) findViewById(R.id.txtphone);
		txtwebsite=  (TextView) findViewById(R.id.txtwebsite);
		//txtadd = (TextView) findViewById(R.id.txtadd);
		//txtdt=  (TextView) findViewById(R.id.txtdarshantime);
		

		header = (TextView) findViewById(R.id.textView1);
		header.setText(name);

	    Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		header.setTypeface(font);
		
		/*address1.setTypeface(font);
		address2.setTypeface(font); 
		time1.setTypeface(font);
		time2.setTypeface(font);
		time3.setTypeface(font); 
		time4.setTypeface(font);
		time5.setTypeface(font);
		time6.setTypeface(font);
		type.setTypeface(font);
		txtemail.setTypeface(font);
		txtphone.setTypeface(font);
		txtwebsite.setTypeface(font);*/
		


		if ("Shree Kalyanraiji Mandir".equals(header.getText())
				&& "Mandvi, Vadodara".equals(str)) {
		
			//Typeface fon = Typeface.createFromAsset(this.getAssets(),
					//"poorich.TTF");
			
			//txtadd.setTypeface(fon);
			//txtadd.setText("Address");
			
			//txtdt.setTypeface(fon);
			//txtdt.setText("Darshan Time");
			
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Bank road, Mandvi,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Vadodara.- 390019,Gujarat, India.");

			
			txtphone.setText("Ph.: 0265 - 2423322, 2427002");
			txtemail.setText("shreekalyanraiji@gmail.com");
			txtwebsite.setText("http://www.google.com/");

			time1.setText("Mangla Darshan :06.30am");
			time2.setText("Shringar Darshan :08.45am");
			time3.setText("Rajbhog Darshan :11.45am");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :05.30pm");
			time6.setText("Shayan Darshan :07.30pm");

			lat = 22.302321;
			lon = 73.210442;

		} else if ("Shree Kalyan Pushti Haveli".equals(header.getText())
				&& "Vastrapur, Ahmedabad".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Nr. Vastrapur Lake,Vastrapur,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Ahmedabad,Gujarat, India.");
			
			txtphone.setText("Ph.: 079-26301080");
			txtemail.setText("kalyanpushtihaveli@gmail.com");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :6.15am to7.15am");
			time2.setText("Shringar Darshan :8.45am to 9.15am");
			time3.setText("Rajbhog Darshan :10.15am to 11.15am");
			time4.setText("Uthapan Darshan :4.30pm to 4.45pm");
			time5.setText("Bhog/Sandhya :5.15pm to 6.00pm");
			time6.setText("Sayan Darshan :7pm to 8pm");

			lat = 23.038483;
			lon = 72.530649;

		} else if ("Shree Mahaprabhuji Baithak".equals(header.getText())
				&& "Naroda, Ahmedabad".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Opp. Devi Multiplex, Naroda,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Ahmedabad – 382325,Gujarat,India.");
			
			txtphone.setText("Ph.: 079-22813444");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.30am ");
			time2.setText("Shringar Darshan :8.30am to 11.30am");
			time3.setText("Rajbhog Darshan :12.00 to 12.30pm");
			time4.setText("Utthapan Darshan :4.30pm");
			time5.setText("Sandhya Darshan :NA");
			time6.setText("Bhog/Shayan Darshan :5.30pm to 6.30pm");

			lat = 23.071977;
			lon = 72.650355;

		}

		else if ("Shree Kalyanraiji Mandir".equals(header.getText())
				&& "Astodia Darwaja, Ahmedabad".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Dhal ki Pole, Astodia Darwaja,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Ahmedabad,Gujarat.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :06.30am ");
			time2.setText("Shringar Darshan :08.45am");
			time3.setText("Rajbhog Darshan :11.45am");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :05.30pm ");
			time6.setText("Shayan Darshan :07.30pm");

			lat = 23.017996;
			lon = 72.590193;

		} else if ("Shree Goverdhannathji Mandir".equals(header.getText())
				&& "Amrutsar, Punjab".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Majith Mandi,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Amrutsar, Punjab.");
			
			txtphone.setText("Ph.: 0183-2547607");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.00am to 7.30am");
			time2.setText("Shringar Darshan :9.30am to 10.00am");
			time3.setText("Rajbhog Darshan :10.45am to 11.00am");
			time4.setText("Utthapan Darshan :4.00pm");
			time5.setText("Sandhya Darshan :NA");
			time6.setText("Shayan Darshan :NA");

			lat = 31.618403;
			lon = 74.871654;

		} else if ("Shree Govardhannathji Haveli".equals(header.getText())
				&& "Secundrabad, Hydrebad".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("108 – Jeera road,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Secundrabad, Hydrebad.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.00am to 7.30am");
			time2.setText("Shringar Darshan :9.30am to 11.30am");
			time3.setText("Rajbhog Darshan :11.30am to 12.00");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :5pm to 6pm");
			time6.setText("Shayan Darshan :7.00pm to 8.00pm");

			lat = 17.426906;
			lon = 78.490176;

		} else if ("Shree Kalyanraiji Mandir".equals(header.getText())
				&& "Gokul, Dist Mathura, U.P".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Satghara ki Gali,B/h Shree Gokulnathji Mandir,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Shreemad Gokul,Dist. Mathura, U.P.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.00am to 7.30am");
			time2.setText("Shringar Darshan :9.30am to 11.30am");
			time3.setText("Rajbhog Darshan :11.30am to 12.00");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :5pm to 6pm");
			time6.setText("Shayan Darshan :7.00pm to 8.00pm");

			lat = 27.43791;
			lon = 77.718371;

		} else if ("Shree Govardhannathji Haveli".equals(header.getText())
				&& "Swami ghat, Dist Mathura, U.P".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Swami ghat");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText(",Dist Mathura,U.P");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.00am to 7.30am");
			time2.setText("Shringar Darshan :9.30am to 11.30am");
			time3.setText("Rajbhog Darshan :11.30am to 12.00 ");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :5pm to 6pm");
			time6.setText("Shayan Darshan :7.00pm to 8.00pm");

			lat = 27.506103;
			lon = 77.683454;

		} else if ("Shree Kalyanraiji Mandir".equals(header.getText())
				&& "Jatipura, Dist Mathura, U.P".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Nr. Navakund,Opp. Birla Guest House,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Jatipura, Dist. Mathura, U.P.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.00am to 7.30am");
			time2.setText("Shringar Darshan :9.30am to 11.30am");
			time3.setText("Rajbhog Darshan :11.30am to 12.00");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :5pm to 6pm");
			time6.setText("Shayan Darshan :7.00pm to 8.00pm");

			lat = 27.475352;
			lon = 77.44103;

		} else if ("Shree Govardhan Gopal Haveli".equals(header.getText())
				&& "Ghadiyali pole, Mandvi, Vadodara".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Desai Sheri,Shreenathji no khacho,Ghadiyali pole,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Mandvi,Vadodara.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :8.00am to 9.00am");
			time2.setText("Shringar Darshan :10.00am to 11.00am");
			time3.setText("Rajbhog Darshan :12.00pm to 12.30pm");
			time4.setText("Utthapan Darshan :5.00pm to 5.30pm");
			time5.setText("Sandhya Darshan :6.00pm to 6.30pm");
			time6.setText("Shayan Darshan :7.00pm to 7.30pm");

			lat = 23.017996;
			lon = 73.208928;

		} else if ("Shree Govardhannathji Haveli".equals(header.getText())
				&& "Manjalpur, Vadodara".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Green park society,b/h MKgandhi school,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Manjalpur,Vadodara.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.30am to 8.30am");
			time2.setText("Shringar Darshan :10.00am to 10.30am");
			time3.setText("Rajbhog Darshan :11.15am to 11.45am");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :NA");
			time6.setText("Shayan Darshan :7.00pm to 7.30pm");

			lat = 22.277902;
			lon = 73.19553;

		} else if ("Shree Govardhannathji Haveli".equals(header.getText())
				&& "Nizampura, Vadodara".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("opp Ghelani Petol Pump,Nizampura,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Vadodara.");

			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.30am to 8.30am");
			time2.setText("Shringar Darshan :10.00am to 10.30am");
			time3.setText("Rajbhog Darshan :11.15am to 11.45am");
			time4.setText("Utthapan Darshan :NA");
			time5.setText("Sandhya Darshan :NA");
			time6.setText("Shayan Darshan :7.00pm to 7.30pm");

			lat = 22.330761;
			lon = 73.180756;

		} else if ("Shree Vallabhdham Mandir".equals(header.getText())
				&& "Ajwa Road, Vadodara".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("Dudheshwar society,Ajwa Road,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Vadodara.");
			
			txtphone.setText("");
			txtemail.setText("");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :8.00am to 9.00am");
			time2.setText("Shringar Darshan :10.00am to 11.00am");
			time3.setText("Rajbhog Darshan :12.00pm to 12.30pm");
			time4.setText("Utthapan Darshan :5.00pm to 5.30pm");
			time5.setText("Sandhya Darshan :6.00pm to 6.30pm");
			time6.setText("Shayan Darshan :7.00pm to 7.30pm");

			lat = 22.300381;
			lon = 73.22182;

		} else if ("Shree Nandalaya".equals(header.getText())
					&& "Harinagar, Vadodara".equals(str)) {
				address1 = (TextView) findViewById(R.id.txtaddress1);
				address1.setText("Gotri road,Harinagar,");
				address2 = (TextView) findViewById(R.id.txtaddress2);
				address2.setText("Vadodara.");
				
				txtphone.setText("");
				txtemail.setText("");
				txtwebsite.setText("");

				time1.setText("Mangla Darshan :7.30am to 8.30am");
				time2.setText("Shringar Darshan :10.00am to 10.30am");
				time3.setText("Rajbhog Darshan :11.15am to 11.45am");
				time4.setText("Utthapan Darshan :NA");
				time5.setText("Sandhya Darshan :NA");
				time6.setText("Shayan Darshan :7.00pm to 7.30pm");

				lat = 22.313824;
				lon = 73.152405;

			} else if ("Pushtidham Haveli".equals(header.getText())
				&& "Ocala, Florida, USA".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("14080 SW 20th Avenue Road,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Ocala. Florida - 34473.");
			
			txtphone.setText("Ph.: 352-307-0065 / 973 - 460- 5936");
			txtemail.setText("pushtidhamocala@gmail.com");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.45am - 8.45am");
			time2.setText("Shringar Darshan :10.15am - 10.30am");
			time3.setText("Rajbhog Darshan :11.30am - 12.30pm");
			time4.setText("Utthapan Darshan :5.00pm - 5.15pm");
			time5.setText("Sandhya Darshan :5.30pm - 6.00pm");
			time6.setText("Shayan Darshan :6.30pm - 7.45pm");

			lat = 28.997749;
			lon = -82.146126;

		} else if ("Shreejidham Haveli".equals(header.getText())
				&& "Lecister, UK".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("504, Melton Road,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Lecister - Le4 7SP.");
			
			txtphone.setText("Ph.: 01162122827");
			txtemail.setText("pustinidhi@yahoo.co.uk");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :7.45am - 8.45am");
			time2.setText("Shringar Darshan :10.15am - 10.30am");
			time3.setText("Rajbhog Darshan :11.30am - 12.30pm");
			time4.setText("Utthapan Darshan :5.00pm - 5.15pm");
			time5.setText("Sandhya Darshan :5.30pm - 6.00pm");
			time6.setText("Shayan Darshan :6.30pm - 7.45pm");

			lat = 52.669536;
			lon = -1.106931;

		} else if ("Gokuldham Haveli".equals(header.getText())
				&& "Atlanta, Georgia, USA".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("2397 Satellite Blvd,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Buford, GA-30518");

			txtphone.setText("Ph.: 770-492-4346");
			txtemail.setText("shrinathjihaveliatlanta@gmail.com");
			txtwebsite.setText("");

			time1.setText("Monday-Sunday 5:30 PM to 7:00 PM");
			time2.setText("Aarti 6:30 PM");
			time3.setText("Saturday & Sunday 10:30 AM to 12:00 PM");
			time4.setText("Aarti 11:30 AM");
		

			lat = 34.085523;
			lon = -83.999131;

		} else if ("Vallabhdham Temple".equals(header.getText())
				&& "Newington, Connecticut, USA".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("26 Church Street,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Newington CT- 06111");
			
			txtphone.setText("Ph.: 860-417-0007,Fax : 860–417–0008");
			txtemail.setText("info@vpofct.org");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :08.00am to 08:30am");
			time2.setText("Rajbhog Darshan :12.00pm to 12.45pm");
			time3.setText("Utthapan Darshan :05.00pm to 06.00pm");
			time4.setText("Sandhya Darshan :07.00pm to 08:30pm");
			time5.setText("Shayan Darshan :07.00pm to 08:30pm");

			lat = 41.672015;
			lon = -72.738313;

		} else if ("Shreemaya Krishnadham".equals(header.getText())
				&& "Milpitas, California, USA".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("BAYVP, 25 Corning Ave.,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Milpitas, CA 95035.");

			txtphone.setText("Ph.:(408) 586-0006");
			txtemail.setText("info@bayvp.org");
			txtwebsite.setText("");

			time1.setText("Monday-Friday :7:30 p.m.");
			time2.setText("Saturday :7:00 p.m.");
			time3.setText("Sunday :5:00 p.m.");
			time4.setText("Sunday Prasad :5:30 p.m.- 6:30 p.m.");

			lat = 37.425046;
			lon = -121.906456;

		} else if ("Shreejidwar".equals(header.getText())
				&& "Chicago, Illinois, USA".equals(str)) {
			address1 = (TextView) findViewById(R.id.txtaddress1);
			address1.setText("440 W Fullerton Ave,");
			address2 = (TextView) findViewById(R.id.txtaddress2);
			address2.setText("Addison, IL 60101");

			txtphone.setText("Ph.:#630-543-3871");
			txtemail.setText("contact@shreejidwar.org");
			txtwebsite.setText("");

			time1.setText("Mangla Darshan :8:00am to 8:30 am");
			time2.setText("Shringar Darshan :11:00am to 11:30am");
			time3.setText("Rajbhog Darshan :12:30pm to 1:00pm");
			time4.setText("Utthapan Darshan :4:30pm to 4:45pm");
			time5.setText("Sandhya Darshan :5:15pm to 5:45pm");
			time6.setText("Shayan Darshan :7:15pm to 7:45pm");

			lat = 41.920411;
			lon = -88.001079;

		}

		else {

		}

		getDirection = (ImageView) findViewById(R.id.imgGetDirection);

		getDirection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// create class object
		        gps = new GPSTracker(Darshantime_Detail.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	String uri = "https://maps.google.com/maps?saddr="+ latitude + "," + longitude+"&daddr="+ lat + "," + lon;
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
		

		txtemail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { txtemail
						.getText().toString() });

				email.setType("message/rfc822");

				startActivity(Intent.createChooser(email, "Send Email"));
			}
		});
		
		txtwebsite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Linkify.addLinks(txtwebsite, Linkify.ALL);
			}
		
		});

	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(),Darshantime.class));
	}
}
