package com.example.k;

import java.util.ArrayList;
import java.util.HashMap;
import com.shreejienterpriseinc.kalyanpushti.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class List_Utsav extends BaseAdapter 
{
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    Context ctx;
 
    public List_Utsav(Activity a, ArrayList<HashMap<String, String>> d) 
    {
    	ctx = a.getBaseContext();
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);       
    }
 
    @Override
	public int getCount() 
    {
        return data.size();
    }
 
    @Override
	public Object getItem(int position) 
    {
        return position;
    }
 
    @Override
	public long getItemId(int position) 
    {
        return position;
    }
 
    @Override
	public View getView(int position, View convertView, ViewGroup parent) 
    {
        View vi=convertView;
    
        if(convertView==null)
            vi = inflater.inflate(R.layout.past_events, null);
 
        TextView date = (TextView)vi.findViewById(R.id.txtdate);
        TextView list = (TextView)vi.findViewById(R.id.txtlist);
        TextView month = (TextView)vi.findViewById(R.id.txtmonth);
       
 
        HashMap<String, String> category = new HashMap<String, String>();
        category = data.get(position);
        
     
     		Typeface font = Typeface
     				.createFromAsset(ctx.getAssets(), "poorich.TTF");

     		
     		date.setTypeface(font);
     		list.setTypeface(font);
     		month.setTypeface(font);
   
 
        // Setting all values in ListView
        if(category.get("Utsav Date")==null)
        	date.setVisibility(View.GONE);
        else
        	date.setText("Date : "+category.get("Utsav Date"));
        
        if(category.get("Utsav List")==null)
        	list.setVisibility(View.GONE);
        else
        	list.setText(category.get("Utsav List"));
        
        if(category.get("Utsav Month")==null)
        	month.setVisibility(View.GONE);
        else
        	month.setText(category.get("Utsav Month"));
        
        
        
        return vi;
     
 }


	
    }

