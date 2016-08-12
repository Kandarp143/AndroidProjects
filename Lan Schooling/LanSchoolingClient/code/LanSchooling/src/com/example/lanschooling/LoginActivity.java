package com.example.lanschooling;

import com.example.lanschooling.R;
import com.socket.SocketClient;
import com.socket.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	public EditText login , pwd;
	public Button loginButton , signup;
	public String lg,pd;
	public SocketClient client;
	ClientSerializable clientObject;
	static LoginActivity loginActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// use of single-tone class
		loginActivity =this;
		
		// to use object of client-serialization class
		clientObject = (ClientSerializable) getIntent().getSerializableExtra("clientObject");
		
		login = (EditText) findViewById(R.id.unmedit);
		pwd = (EditText) findViewById(R.id.pwdedit);
		
		loginButton = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		
		// when user click on login button
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				lg = login.getText().toString();
				pd = pwd.getText().toString();
				
				ClientSerializable.unmLogin = lg;		
				
				if(!lg.equals("") && !pd.equals("")){
					clientObject.client.send(new Message("login",lg,pd, "SERVER"));
					
		        }
				
				else{
					Toast.makeText(getApplicationContext(), "Please enter Usrname and Password", Toast.LENGTH_SHORT).show();
				}
			}

		});
		
		// when user click on signup button
		signup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				lg = login.getText().toString();
				pd = pwd.getText().toString();
				
				
				ClientSerializable.unmLogin = lg;		
				
				if(!lg.equals("") && !pd.equals("")){
					clientObject.client.send(new Message("signup",lg,pd, "SERVER"));
		        }
				
				else{
					Toast.makeText(getApplicationContext(), "Please enter Usrname and Password", Toast.LENGTH_SHORT).show();
				}
			
			}

		});
		
	}
	
	
	//use of interfcae to get view by othe class
	public static LoginActivity getInstance(){
		   return   loginActivity;
	}
	
	// by checking credential for login and to pass to the next activity
	public void authenticationLogin(String state){
		
		if(state.equalsIgnoreCase("TRUE")){
			Toast.makeText(getApplicationContext(), "Login Successful\n", Toast.LENGTH_SHORT).show();
			Intent in = new Intent(LoginActivity.this,ListOfUsersActivity.class);
            in.putExtra("clientObject",clientObject);
            startActivity(in);
		}
		else{
			Toast.makeText(getApplicationContext(), "Login Failed\n Please Enter Correct Details", Toast.LENGTH_SHORT).show();
		}
				
	}
	
	// by checking credential for signup and to pass to the next activity
     public void authenticationSignUp(String state1){
		
		if(state1.equalsIgnoreCase("TRUE")){
			Toast.makeText(getApplicationContext(), "Singup Successful \n", Toast.LENGTH_SHORT).show();
			Intent in = new Intent(LoginActivity.this,ListOfUsersActivity.class);
            in.putExtra("clientObject",clientObject);
            startActivity(in);
		}
		else{
			Toast.makeText(getApplicationContext(), "Singup Failed \n Please Enter Details Again", Toast.LENGTH_SHORT).show();
		}
				
	}
     
    // by closing socket and all open connection exit from application
     @Override
 	public void onBackPressed() {
    	 
    	 //signout from server
    	 clientObject.client.send(new Message("message", ClientSerializable.unmLogin, ".bye", "SERVER"));
    	 
    	 //remove all data.
    	 ClientSerializable.allObjectArray.clear();
    	 ClientSerializable.LoginUserName.clear();
    	 ClientSerializable.unmLogin = "";
    	 ClientSerializable.globalMsgSender = "";
    	 ClientSerializable.globalMsgSenderTime = "";
    	 ClientSerializable.senderName = "";
    	 ClientSerializable.signoutUser = "";
    	 ClientSerializable.selectedChatUser = "";
    	 
    	 // application close
    	 finish();
    	 System.exit(0);
    	 
    	 
 	}
}
