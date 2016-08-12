package com.example.k;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class List_Activities extends BaseAdapter {
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	Context ctx;

	public List_Activities(Activity a, ArrayList<HashMap<String, String>> d) {
		ctx = a.getBaseContext();
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		if (convertView == null)
			vi = inflater.inflate(R.layout.list_activities, null);

		TextView name = (TextView) vi.findViewById(R.id.name);
		TextView type = (TextView) vi.findViewById(R.id.type);
		ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image);

		HashMap<String, String> category = new HashMap<String, String>();
		category = data.get(position);

		// Setting all values in ListView
		Typeface font = Typeface
				.createFromAsset(ctx.getAssets(), "poorich.TTF");

		name.setText(category.get("God"));
		name.setTypeface(font);
		type.setText(category.get("Type"));
		type.setTypeface(font);

		if (name.getText().equals(
				"Shree Vallabha Vishwavihar Education and Charitable Trust"))
			thumb_image.setImageResource(R.drawable.aone);
		if (name.getText().equals(
				"Shree Kalyanraiji Sarvajanik Charitable Trust"))
			thumb_image.setImageResource(R.drawable.atwo);
		if (name.getText().equals(
				"Vakpatishri Vallbhacharaya Mahaprabhuji Navanidhi Charitable Trust"))
			thumb_image.setImageResource(R.drawable.athree);
		if (name.getText().equals(
				"Pushti Prabha Memorial Trust"))
			thumb_image.setImageResource(R.drawable.afour);
		if (name.getText().equals(
				"Vadodara Society for Prevention of  Cruelty to Animals (VSPCA)"))
			thumb_image.setImageResource(R.drawable.afive);
		if (name.getText().equals(
				"Shree Sarvottam Charitable Trust"))
			thumb_image.setImageResource(R.drawable.asix);
		if (name.getText().equals(
				"Jayantilal Chand Charitable Trust"))
			thumb_image.setImageResource(R.drawable.aeight);
		if (name.getText().equals(
				"Shree Vallabhacharya Kalyan Krupa Trust"))
			thumb_image.setImageResource(R.drawable.anine);
		if (name.getText().equals(
				"Pushti Parivar of Mumbai"))
			thumb_image.setImageResource(R.drawable.aten);
		if (name.getText().equals(
				"Vaishnav Parivar of New Zealand"))
			thumb_image.setImageResource(R.drawable.aseven);

		return vi;
	}
	
}
