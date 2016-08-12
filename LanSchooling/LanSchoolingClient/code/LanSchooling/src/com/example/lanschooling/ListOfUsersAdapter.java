package com.example.lanschooling;


import java.util.ArrayList;
import com.example.lanschooling.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListOfUsersAdapter extends BaseAdapter{

	    private Activity activity;
	    private ArrayList<UserDetails> data;
	    private static LayoutInflater inflater=null;
	    Bitmap bitmap = null;
	    int difference;

	 public ListOfUsersAdapter(Activity a, ArrayList<UserDetails> userList) {
	     activity = a;
	     data=userList;
	     inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
	 }

  
	public int getCount() {
		
       return data.size();
    }

   public Object getItem(int position) {
       return position;
   }

   public long getItemId(int position) {
       return position;
   }
   
   // adapter to fill listview (contains list of username and unread messages)
   public View getView(final int position, View convertView, ViewGroup parent) {
       View vi=convertView;
       if(convertView==null)
           vi = inflater.inflate(R.layout.list_item_online, null);

        TextView userName = (TextView) vi.findViewById(R.id.textViewListItem);	
        userName.setText(data.get(position).getUsername().toString());
        
        TextView unreadMsg = (TextView) vi.findViewById(R.id.unreadMsg);	
        unreadMsg.setText(data.get(position).getChatcount().toString());

       return vi;
   }
   
   
	}