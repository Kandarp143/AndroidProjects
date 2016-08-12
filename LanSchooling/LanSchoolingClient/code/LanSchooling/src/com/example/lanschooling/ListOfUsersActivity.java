package com.example.lanschooling;


import com.example.lanschooling.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListOfUsersActivity extends Activity {
	
	  ListView list;
	  ListOfUsersAdapter adapter;
	  String unm;
	  ClientSerializable clientObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online_users);
		
		// to use object of client-serialization class
		clientObject = (ClientSerializable) getIntent().getSerializableExtra("clientObject");
		
		ClientSerializable.currentChatUserName = "";
		
		list=(ListView)findViewById(R.id.listView1);
	    adapter = new ListOfUsersAdapter(ListOfUsersActivity.this, clientObject.LoginUserName);      
	    list.setAdapter(adapter);
	   
	    	// to fill the listview by updating connected users with server at each second
		    final Handler handler = new Handler();
		    handler.postDelayed( new Runnable() {
	
		        @Override
		        public void run() {
		        	for (int i = 0; i < ClientSerializable.LoginUserName.size(); i++) {
		        		Integer count = 0;
		    			for (int j = 0; j < ClientSerializable.allObjectArray.size(); j++) {
		    				if(ClientSerializable.LoginUserName.get(i).getUsername().equalsIgnoreCase(ClientSerializable.allObjectArray.get(j).getName()) && 
		    						ClientSerializable.allObjectArray.get(j).getIsRead()==false)
		    				{
		    					count++;
		    				}
		    			}
		    			if(count==0)
		    			{
		    				ClientSerializable.LoginUserName.get(i).setChatcount("");
		    			}
		    			else
		    			{
		    				ClientSerializable.LoginUserName.get(i).setChatcount(""+count);
		    			}
		    		}
		        	
		            adapter.notifyDataSetChanged();
		            handler.postDelayed( this, 1000 );
		        }
		    }, 1000 );
	    
		    // when click on particular user and to make chat with him/her
	        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        	@Override
				public void onItemClick(AdapterView<?> parent, View view,
						 int position, long id) {		
	        		
	        		TextView textView = (TextView) view.findViewById(R.id.textViewListItem); 
	        	    String text = textView.getText().toString(); 
	                
        	        Intent intent = new Intent(ListOfUsersActivity.this, MessageActivity.class);
        	        intent.putExtra("selected_Target", text);
        	        ClientSerializable.currentChatUserName = text;
        	        intent.putExtra("clientObjectt", clientObject);
        	        startActivity(intent);

	        	}
	          });
	        
	        
	}
	
	 @Override
	 	public void onBackPressed() {
	    	 
		 new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle("Exit Application")
			.setMessage("Are you sure you want to close the application.?")
			.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							finish();
							System.exit(0);
						}
					}).setNegativeButton("No", null).show();
          }

}
