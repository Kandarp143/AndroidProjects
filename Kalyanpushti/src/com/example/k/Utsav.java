package com.example.k;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Utsav extends Activity
{
	public static String header1 = null;

	ArrayList<HashMap<String, String>> godList;
	public TextView tv;
	
	ListView list = null;
	List_Utsav adapter = null;
	public static int fcount = 0;
	int i,j;
	
	ProgressDialog mLoadingDialog;
	private final static int DIALOG_TASKING = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.utsav);
		
		tv = (TextView) findViewById(R.id.textView1);
		Typeface font = Typeface.createFromAsset(this.getAssets(),"poorich.TTF");
		tv.setTypeface(font);
		
		godList = new ArrayList<HashMap<String, String>>();
		
		showDialog(DIALOG_TASKING);		
		new DownloadTask().execute("");

	}
	
	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch (id) {
		case DIALOG_TASKING:
			mLoadingDialog = new ProgressDialog(this);
			mLoadingDialog.setMessage("Loading Utsav...");
			mLoadingDialog.setCancelable(true);
			return mLoadingDialog;
		}
		return super.onCreateDialog(id);
	}
	
	private class DownloadTask extends AsyncTask<String, Void, String>
	{
		@Override
		protected String doInBackground(String... params)
		{
			try
			{
				URL url3 = new URL("http://www.shreejienterpriseinc.com/shreeji1/kalyanpushtiutsavlist.xml");
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new InputSource(url3.openStream()));
				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("pushti");
				Log.d("", ""+nodeList.getLength());

				for (int i = 0; i < nodeList.getLength(); i++)
				{

					Node node = nodeList.item(i);

					HashMap<String, String> map = new HashMap<String, String>();

					Element fstElmnt = (Element) node;
					
					NodeList nameList = fstElmnt.getElementsByTagName("date");
					Element nameElement = (Element) nameList.item(0);
					nameList = nameElement.getChildNodes();

					NodeList UtsavList = fstElmnt.getElementsByTagName("utsavlist");
					Element websiteElement = (Element) UtsavList.item(0);
					UtsavList = websiteElement.getChildNodes();

					NodeList GujMonth = fstElmnt.getElementsByTagName("gujmonth");
					Element gujmonthElement = (Element) GujMonth.item(0);
					GujMonth = gujmonthElement.getChildNodes();

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
							Locale.getDefault()); // Set your date format
					Date listDate = sdf.parse(nameList.item(0).getNodeValue());
					// Toast.makeText(getBaseContext(), "date : "+listDate,
					// Toast.LENGTH_LONG).show();

					java.util.Calendar calc = java.util.Calendar.getInstance();
					String formattedDate = sdf.format(calc.getTime());
					Date CurDate = sdf.parse(formattedDate);
					// Toast.makeText(getBaseContext(), "date1 : "+CurDate,
					// Toast.LENGTH_LONG).show();

					if (listDate.compareTo(CurDate) > 0) {
						j++;

						map.put("Utsav Date", nameList.item(0).getNodeValue());
						map.put("Utsav List", UtsavList.item(0).getNodeValue());
						map.put("Utsav Month", GujMonth.item(0).getNodeValue());
						godList.add(map);
					} 
					else if (listDate.compareTo(CurDate) < 0) {
						
					}
					else
					{
						j++;

						map.put("Utsav Date", ((Node) nameList.item(0)).getNodeValue());
						map.put("Utsav List", ((Node) UtsavList.item(0)).getNodeValue());
						map.put("Utsav Month", ((Node) GujMonth.item(0)).getNodeValue());
						godList.add(map);
					}

					fcount = nodeList.getLength();
				}
			}
			catch(Exception e){}
			return "";
		}
		protected void onPostExecute(String result)
		{
			runOnUiThread(new Runnable()
			{
			    public void run()
			    {
			    	//TextView tv = (TextView) findViewById(R.id.textView1);
					tv.setText("Utsav (" + j + ")");
					//Typeface font = Typeface.createFromAsset(this.getAssets(),"poorich.TTF");
					//tv.setTypeface(font);

					list = (ListView) findViewById(R.id.listView1);
					list.setBackgroundResource(R.drawable.shape);
					// Getting adapter by passing xml data ArrayList
					adapter = new List_Utsav(Utsav.this, godList);
					list.setAdapter(adapter);
		  			adapter.notifyDataSetChanged();
			    }
			 });
			
			dismissDialog(DIALOG_TASKING);
		}

		@Override
		protected void onPreExecute()
		{
		}

		@Override
		protected void onProgressUpdate(Void... values)
		{
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Main.class));
	}
}
