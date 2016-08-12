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

public class List_Pushtimarg extends BaseAdapter {
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	Context ctx;

	public List_Pushtimarg(Activity a, ArrayList<HashMap<String, String>> d) {
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
			vi = inflater.inflate(R.layout.list_pushtimarg, null);

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

		if (name.getText().equals("Shree Kalyanraiji"))
			thumb_image.setImageResource(R.drawable.kalyanraiji);
		if (name.getText().equals("Shree Mahaprabhuji"))
			thumb_image.setImageResource(R.drawable.mahaprabhu);
		if (name.getText().equals("Shree Mahaprabhuji"))
			thumb_image.setImageResource(R.drawable.vanshavali);
		if (name.getText().equals("Shree Yamunaji"))
			thumb_image.setImageResource(R.drawable.yamunaji);
		if (name.getText().equals("Shree Gopinathji"))
			thumb_image.setImageResource(R.drawable.gopinathji);
		if (name.getText().equals("Shree Gusaiji"))
			thumb_image.setImageResource(R.drawable.gusaiji);
		if (name.getText().equals("Shree Yadunathji"))
			thumb_image.setImageResource(R.drawable.yadunathji);
		if (name.getText().equals("Shree Dwarkeshlalji Maharajshree"))
			thumb_image.setImageResource(R.drawable.dwarkeshlalji);

		return vi;
	}
	
	

	
}