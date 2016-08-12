package com.example.k;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Darshantime extends Activity {
	public static String header1;

	ListView list = null;
	List_Darshantime adapter = null;
	String god[] = { "Shree Kalyanraiji Mandir", "Shree Kalyan Pushti Haveli",
			"Shree Mahaprabhuji Baithak", "Shree Kalyanraiji Mandir",
			"Shree Goverdhannathji Mandir", "Shree Govardhannathji Haveli",
			"Shree Kalyanraiji Mandir", "Shree Govardhannathji Haveli",
			"Shree Kalyanraiji Mandir", "Shree Govardhan Gopal Haveli",
			"Shree Govardhannathji Haveli", "Shree Govardhannathji Haveli",
			"Shree Vallabhdham Mandir","Shree Nandalaya", "Pushtidham Haveli",
			"Shreejidham Haveli", "Gokuldham Haveli", "Vallabhdham Temple",
			"Shreemaya Krishnadham", "Shreejidwar" };
	String type[] = { "Mandvi, Vadodara", "Vastrapur, Ahmedabad",
			"Naroda, Ahmedabad", "Astodia Darwaja, Ahmedabad", "Amrutsar, Punjab",
			"Secundrabad, Hydrebad", "Gokul, Dist Mathura, U.P",
			"Swami ghat, Dist Mathura, U.P", "Jatipura, Dist Mathura, U.P",
			"Ghadiyali pole, Mandvi, Vadodara", "Manjalpur, Vadodara",
			"Nizampura, Vadodara", "Ajwa Road, Vadodara", "Harinagar, Vadodara","Ocala, Florida, USA",
			"Lecister, UK", "Atlanta, Georgia, USA", "Newington, Connecticut, USA",
			"Milpitas, California, USA", "Chicago, Illinois, USA" };
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.darshantime);

		TextView tv = (TextView) findViewById(R.id.textView1);
		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		tv.setTypeface(font);

		final ArrayList<HashMap<String, String>> godList = new ArrayList<HashMap<String, String>>();

		for (int k = 0; k < god.length; k++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();

			// adding each child node to HashMap key =&gt; value
			map.put("God", god[k]);
			map.put("Type", type[k]);

			// adding HashList to ArrayList
			godList.add(map);
		}

		list = (ListView) findViewById(R.id.listView1);
		list.setBackgroundResource(R.drawable.shape);
		// Getting adapter by passing xml data ArrayList
		adapter = new List_Darshantime(this, godList);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				Intent i = new Intent(getApplicationContext(),
						Darshantime_Detail.class);
				i.putExtra("Header", god[position]);
				i.putExtra("Type", type[position]);
				startActivity(i);
			}

		});

		adapter = new List_Darshantime(this, godList);
		list.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Main.class));
	}
}
