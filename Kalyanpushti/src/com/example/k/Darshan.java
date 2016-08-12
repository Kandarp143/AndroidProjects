package com.example.k;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Darshan extends Activity {

	protected static final int TIME_DIALOG_ID = 0;
	TextView ashtsama,varshotsav,tv;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.darshan);

		addListenerOnButton();
		
		tv = (TextView) findViewById(R.id.textView1);
		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		ashtsama.setTypeface(font);
		varshotsav.setTypeface(font);
		tv.setTypeface(font);

	}

	public void addListenerOnButton() {

		ashtsama = (TextView) findViewById(R.id.txtview1);
		varshotsav = (TextView) findViewById(R.id.txtview2);
		

		ashtsama.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
				startActivity(new Intent(Darshan.this, Ashthsama.class));

			}

		});

		varshotsav.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
				startActivity(new Intent(Darshan.this, Varshotsav.class));

			}

		});

	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Main.class));
	}

}
