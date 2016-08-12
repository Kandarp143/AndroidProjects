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

public class List_Darshantime extends BaseAdapter {
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	Context ctx;

	public List_Darshantime(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.list_darshantime, null);

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

		if ((name.getText().equals("Shree Kalyanraiji Mandir") && type
				.getText().equals("Mandvi, Vadodara")))
			thumb_image.setImageResource(R.drawable.one);
		if ((name.getText().equals("Shree Kalyan Pushti Haveli") && type
				.getText().equals("Vastrapur, Ahmedabad")))
			thumb_image.setImageResource(R.drawable.two);
		if ((name.getText().equals("Shree Mahaprabhuji Baithak") && type
				.getText().equals("Naroda, Ahmedabad")))
			thumb_image.setImageResource(R.drawable.three);
		if ((name.getText().equals("Shree Kalyanraiji Mandir") && type
				.getText().equals("Astodia Darwaja, Ahmedabad")))
			thumb_image.setImageResource(R.drawable.one);
		if ((name.getText().equals("Shree Goverdhannathji Mandir") && type
				.getText().equals("Amrutsar, Punjab")))
			thumb_image.setImageResource(R.drawable.five);
		if ((name.getText().equals("Shree Govardhannathji Haveli") && type
				.getText().equals("Secundrabad, Hydrebad")))
			thumb_image.setImageResource(R.drawable.six);
		if ((name.getText().equals("Shree Kalyanraiji Mandir") && type
				.getText().equals("Gokul, Dist Mathura, U.P")))
			thumb_image.setImageResource(R.drawable.seven);
		if ((name.getText().equals("Shree Govardhannathji Haveli") && type
				.getText().equals("Swami ghat, Dist Mathura, U.P")))
			thumb_image.setImageResource(R.drawable.sixteen);
		if ((name.getText().equals("Shree Kalyanraiji Mandir") && type
				.getText().equals("Jatipura, Dist Mathura, U.P")))
			thumb_image.setImageResource(R.drawable.eight);
		if ((name.getText().equals("Shree Govardhan Gopal Haveli") && type
				.getText().equals("Ghadiyali pole, Mandvi, Vadodara")))
			thumb_image.setImageResource(R.drawable.seventeen);
		if ((name.getText().equals("Shree Govardhannathji Haveli") && type
				.getText().equals("Manjalpur, Vadodara")))
			thumb_image.setImageResource(R.drawable.manjalpur);
		if ((name.getText().equals("Shree Govardhannathji Haveli") && type
				.getText().equals("Nizampura, Vadodara")))
			thumb_image.setImageResource(R.drawable.nine);
		if ((name.getText().equals("Shree Vallabhdham Mandir") && type
				.getText().equals("Ajwa Road, Vadodara")))
			thumb_image.setImageResource(R.drawable.eighteen);
		if ((name.getText().equals("Shree Nandalaya") && type.getText()
				.equals("Harinagar, Vadodara")))
			thumb_image.setImageResource(R.drawable.nineteen);
		if ((name.getText().equals("Pushtidham Haveli") && type.getText()
				.equals("Ocala, Florida, USA")))
			thumb_image.setImageResource(R.drawable.ten);
		if ((name.getText().equals("Shreejidham Haveli") && type.getText()
				.equals("Lecister, UK")))
			thumb_image.setImageResource(R.drawable.eleven);
		if ((name.getText().equals("Gokuldham Haveli") && type.getText()
				.equals("Atlanta, Georgia, USA")))
			thumb_image.setImageResource(R.drawable.twele);
		if ((name.getText().equals("Vallabhdham Temple") && type.getText()
				.equals("Newington, Connecticut, USA")))
			thumb_image.setImageResource(R.drawable.thirteen);
		if ((name.getText().equals("Shreemaya Krishnadham") && type.getText()
				.equals("Milpitas, California, USA")))
			thumb_image.setImageResource(R.drawable.fourteen);
		if ((name.getText().equals("Shreejidwar") && type.getText().equals(
				"Chicago, Illinois, USA")))
			thumb_image.setImageResource(R.drawable.fifteen);

		return vi;
	}
	
	

}