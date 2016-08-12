package com.example.k;


import com.shreejienterpriseinc.kalyanpushti.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebSite extends Activity
{
	WebView picView;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.website);

		picView = (WebView) findViewById(R.id.webView1);
		tv = (TextView) findViewById(R.id.textView1);
		
		
		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		tv.setTypeface(font);

		picView.setBackgroundColor(0);
		picView.getSettings().setBuiltInZoomControls(true);
		picView.getSettings().setUseWideViewPort(true);
		picView.getSettings().setLoadWithOverviewMode(true);

		picView.setWebViewClient(new MyWebViewClient());
        
        openURL();
	}
	
	private class MyWebViewClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			return true;
		}
	}
	
	private void openURL()
	{

		picView.loadUrl("http://shreekalyanpushti.org/");
        picView.requestFocus();
    }
}