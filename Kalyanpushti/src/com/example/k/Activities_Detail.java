package com.example.k;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

@SuppressLint("DefaultLocale")
public class Activities_Detail extends Activity {
	TextView header;
	WebView disp;

	String str, name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mahaprabhuji_detail);

		Intent i = getIntent();
		name = i.getStringExtra("Header");
		str = i.getStringExtra("Type");

		header = (TextView) findViewById(R.id.textView1);
		header.setText(name);
		

		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		header.setTypeface(font);

		disp = (WebView) findViewById(R.id.webView1);
		disp.getSettings().setBuiltInZoomControls(true);

		if ("Shree Vallabha Vishwavihar Education and Charitable Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"shreevallabhavishwavihareducationandcharitabletrust.html");
		  
	    }
		else if ("Shree Kalyanraiji Sarvajanik Charitable Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"shreekalyanraijisarvajanikcharitabletrust.html");
	    }
		else if ("Vakpatishri Vallbhacharaya Mahaprabhuji Navanidhi Charitable Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"vakpatishrivallbhacharayamahaprabhujinavanidhicharitabletrust.html");
	    }
		else if ("Pushti Prabha Memorial Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"pushtiprabhamemorialtrust.html");
	    }
		else if ("Vadodara Society for Prevention of  Cruelty to Animals (VSPCA)".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"vadodarasocietyforpreventionofcrueltytoanimals.html");
	    }
		else if ("Shree Sarvottam Charitable Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"shreesarvottamcharitabletrust.html");
	    }
		else if ("Jayantilal Chand Charitable Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"jcct.html");
	    }
		else if ("Shree Vallabhacharya Kalyan Krupa Trust".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"vallabhacharyakalyankrupatrust.html");
	    }
		else if ("Pushti Parivar of Mumbai".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"pushtiparivarmumbai.html");
	    }
		else if ("Vaishnav Parivar of New Zealand".equals(header.getText()))
		{
		   disp.loadUrl("file:///android_asset/" +"vpnz.html");
	    }
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(),Activities.class));
	}
}