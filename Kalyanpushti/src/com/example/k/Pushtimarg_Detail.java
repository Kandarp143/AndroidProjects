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
public class Pushtimarg_Detail extends Activity {
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

		if ("Shree Kalyanraiji".equals(header.getText())
				&& "History".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"kalyanraijihistory.html");
	    }
		else if ("Shree Mahaprabhuji".equals(header.getText())
				&& "Lifesketch & Education".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"mahaprabhujihistorybirth.htm");
	    }
		else if ("Shree Mahaprabhuji".equals(header.getText())
				&& "Vanshavali".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"Shreevanshavali.html");
	    }
		else if ("Shree Yamunaji".equals(header.getText())
				&& "Lifesketch".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"yamunaji_lifesktech.html");
	    }
		else if ("Shree Gopinathji".equals(header.getText())
				&& "Lifesketch".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"gopinathji_lifesketch.html");
	    }
		else if ("Shree Gusaiji".equals(header.getText())
				&& "Lifesketch & Family".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"gusaiji_lifesketch.html");
	    }
		else if ("Shree Yadunathji".equals(header.getText())
				&& "Lifesketch".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"yadunathji.html");
	    }
		else if ("Shree Dwarkeshlalji Maharajshree".equals(header.getText())
				&& "Lifesketch".equals(str))
		{
		   disp.loadUrl("file:///android_asset/" +"shreedwarkeshlaljimaharajshree.html");
	    }
		else
		{
			
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(),Pushtimarg.class));
	}
}