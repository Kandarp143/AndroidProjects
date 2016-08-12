package com.example.k;

import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Varshotsav extends Activity {
	public static int mMonth;
	public static int currentIndex = -1;
	protected static final int January = 0;
	protected static final int February = 0;
	protected static final int March = 0;
	protected static final int April = 0;
	protected static final int May = 0;
	protected static final int June = 0;
	protected static final int July = 0;
	protected static final int August = 0;
	protected static final int September = 0;
	protected static final int October = 0;
	protected static final int November = 0;
	protected static final int December = 0;
	ImageView img;
	TextView header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.darshanimage);

		img = (ImageView) findViewById(R.id.imageView1);
		//header = (TextView) findViewById(R.id.textView1);

		img.setScaleType(ScaleType.FIT_XY);

		//Typeface font = Typeface.createFromAsset(this.getAssets(),
				//"poorich.TTF");
		//header.setTypeface(font);
		
	    	Calendar mCalendar = Calendar.getInstance();
			mMonth = mCalendar.get(Calendar.MONTH) + 1;
			
			if (mMonth == 1) {
				
					//header.setText("January");
					img.setImageResource(R.drawable.jan);	    				
			
		    } 
			else if (mMonth == 2) {

					//header.setText("February");
					img.setImageResource(R.drawable.jan);

		    }
			else if (mMonth == 3) {

					//header.setText("March");
					img.setImageResource(R.drawable.march);

		    }
			else if (mMonth == 4) {

					//header.setText("April");
					img.setImageResource(R.drawable.march);

		    }
			else if (mMonth == 5) {

				//header.setText("May");
				img.setImageResource(R.drawable.may);

		    }
			else if (mMonth == 6) {

				//header.setText("June");
				img.setImageResource(R.drawable.june);

		    }
			else if (mMonth == 7) {

				//header.setText("July");
				img.setImageResource(R.drawable.july);

		    }
			else if (mMonth == 8) {

				//header.setText("August");
				img.setImageResource(R.drawable.aug);

		    }
			else if (mMonth == 9) {

				//header.setText("September");
				img.setImageResource(R.drawable.sept);

		    }
			else if (mMonth == 10) {

				//header.setText("October");
				img.setImageResource(R.drawable.oct);

		    }
			else if (mMonth == 11) {

				//header.setText("November");
				img.setImageResource(R.drawable.nov);

		    }
			else if (mMonth == 12) {

				//header.setText("December");
				img.setImageResource(R.drawable.dec);

			}
	    }

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Darshan.class));
	}
}
