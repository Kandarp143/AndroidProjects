package com.example.lanschooling;

import java.io.File;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.lanschooling.R;
import com.socket.Download;
import com.socket.Message;

public class MessageActivity extends Activity {
	
	public TextView sen,rec ;
	public TextView senmsgTime,recmsgTime;
	public LinearLayout linerLayoutView;
	public ClientSerializable clientObject;
	TextView header;
	EditText msg;
	Button send;
	String messageSender;
    static MessageActivity messageAct;
	String targetUser = "";
	ImageView attach;
	public File file;
	private static final int PICKFILE_RESULT_CODE = 1;
	AlertDialog.Builder builder ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		messageAct = this;
		
		// get name of user whise chat window is open
		Intent intent = getIntent();
		targetUser = intent.getStringExtra("selected_Target");
		ClientSerializable.selectedChatUser = targetUser; 
		
	    clientObject = (ClientSerializable) getIntent().getSerializableExtra("clientObject");
	
		
		linerLayoutView = (LinearLayout) findViewById(R.id.chatinfo);
		header = (TextView)findViewById(R.id.header);
		msg = (EditText) findViewById(R.id.msg);
		send = (Button)findViewById(R.id.sendMsg);
		attach = (ImageView) findViewById(R.id.imageView1);
		
		header.setText(targetUser);
		
		// when user send message to anyone
		
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				messageSender = msg.getText().toString();
			
				if(!ClientSerializable.unmLogin.equals("") && !targetUser.equals("") ){
					 msg.setText("");
					 clientObject.client.send(new Message("message", ClientSerializable.unmLogin, messageSender, targetUser));
		        }
						
			}

		});
		
		// when user attach file to send as message to another user
		attach.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	    	
		    	 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	             intent.setType("*/*");
	             startActivityForResult(intent,PICKFILE_RESULT_CODE);
		    }
		});
		
		// for showing messages which is send or receive
		for (int i = 0; i < ClientSerializable.allObjectArray.size(); i++) {
			
			System.out.println("name:->"+targetUser + " :-> " +ClientSerializable.allObjectArray.get(i).getChatmessage());
			
			if(ClientSerializable.allObjectArray.get(i).getName().equalsIgnoreCase(targetUser))
			{
				ClientSerializable.allObjectArray.get(i).setIsRead(true);
				TextView valueTV = new TextView(this);
		    	valueTV.setText(ClientSerializable.allObjectArray.get(i).getChatmessage());
			    valueTV.setLayoutParams(new LayoutParams(
			            LayoutParams.FILL_PARENT,
			            LayoutParams.WRAP_CONTENT));
	
			    ((LinearLayout) linerLayoutView).addView(valueTV);
			}
		}
		
		// to set unread message count
		for (int j = 0; j < ClientSerializable.LoginUserName.size(); j++) {
			if(ClientSerializable.LoginUserName.get(j).getUsername().equalsIgnoreCase(targetUser))
			{
				ClientSerializable.LoginUserName.get(j).setChatcount("");
			}
		}

	}
	
	// interface
	public static MessageActivity getInstance(){
		   return   messageAct;
		 }

	// method to make dynamic textview to show messages
	public void addTextView(){
		if(ClientSerializable.senderName.equalsIgnoreCase(targetUser))
		{
			TextView valueTV = new TextView(this);
		    valueTV.setText(ClientSerializable.globalMsgSender);
		    valueTV.setLayoutParams(new LayoutParams(
		            LayoutParams.FILL_PARENT,
		            LayoutParams.WRAP_CONTENT));
	
		    ((LinearLayout) linerLayoutView).addView(valueTV);
	    
		}
	}
	
	public void closeConncetion(){
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Exit Application")
		.setMessage("Are you sure you want to close the socket?")
		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						finish();
						System.exit(0);
					}
				}).create().show();
    }
	
	// when select any file from sdcard this method will show you path name
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		  switch(requestCode){
		  	case PICKFILE_RESULT_CODE:
			   if(resultCode==RESULT_OK){
			               
				Uri uri = data.getData();
				String selectedImagePath = ImageFilePath.getPath(getApplicationContext(), uri);
			    String[] splitArray = selectedImagePath.split("/");
			    System.out.println("file name : " + selectedImagePath);
			    ClientSerializable.filePath=splitArray[splitArray.length-1];
			    ClientSerializable.absolutefilePath=selectedImagePath;

			    clientObject.client.send(new Message("upload_req", ClientSerializable.unmLogin,ClientSerializable.filePath, targetUser));
			   }
		  }
	  
	 }
	
	public void fileUploadReq(){
		builder = new AlertDialog.Builder(this);

		builder.setTitle("Do this action");
		builder.setMessage("do you want Accept file "+ ClientSerializable.filePath +" from "+ClientSerializable.senderName + " ?");

		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int which) {
		    	
		    	ClientSerializable.dialogBoxStatus = true;
		    	
		    	ClientSerializable.uploadFile = new File(ClientSerializable.msgContent);
            	 
            	File sdCard = Environment.getExternalStorageDirectory();
 		    	File dir = new File (sdCard.getAbsolutePath() + "/LanSchooling");
 		    	dir.mkdirs();
 		    	File file = new File(dir, ClientSerializable.msgContent);
 		    	
 		    	Download dwn = new Download(file.getPath());
                Thread t = new Thread(dwn);
                t.start();
                 
 		    	clientObject.client.send(new Message("upload_res", ClientSerializable.unmLogin,""+dwn.port, ClientSerializable.senderName));
		    	dialog.dismiss();
		    }

		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	ClientSerializable.dialogBoxStatus = false;
		    	clientObject.client.send(new Message("upload_res", ClientSerializable.unmLogin,"NO", ClientSerializable.senderName));
		        dialog.dismiss();
		    }
		});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
	 @Override
	 	public void onBackPressed() {
		 finish();
		 startActivity(new Intent(getApplicationContext(), ListOfUsersActivity.class));
	 }
}
