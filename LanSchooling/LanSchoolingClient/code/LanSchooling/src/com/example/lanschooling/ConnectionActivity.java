package com.example.lanschooling;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.socket.SocketClient;

public class ConnectionActivity extends Activity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public EditText ip,portnum;
	public Button connect;
	public String serverAddr,portAddr;
	public int port;
    public SocketClient client;
    public Thread clientThread;
    public static ConnectionActivity connectionAct;

    
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection);
		
		// to handle network on main thread exception
		if (android.os.Build.VERSION.SDK_INT > 9) {
			   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			   StrictMode.setThreadPolicy(policy);
			}
		
		ip = (EditText) findViewById(R.id.editText1);
		portnum = (EditText) findViewById(R.id.editText2);
		
		alert();
			
		addListenerOnButton();
	}
	
	// To make connection with server 
	public void addListenerOnButton() {

		connect = (Button) findViewById(R.id.button1);
		
		connect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			
				 serverAddr = ip.getText().toString();
				 portAddr = portnum.getText().toString();
				 
				 port = Integer.parseInt(portAddr);
				 
				 client = new SocketClient();
				 
			        
			        if(!serverAddr.equals("") && port != 0){
			            try{
			            	
			            	try{
			            		 client.connectionMethod(serverAddr,port);
			            	}
			               		                
			                catch (UnknownHostException e1) {
			                	Toast.makeText(getApplicationContext(), "Server not found\n"+e1, Toast.LENGTH_SHORT).show();
			                    e1.printStackTrace();
			                    
			                 } catch (IOException e1) {
			                	Toast.makeText(getApplicationContext(), "Server not found\n"+e1, Toast.LENGTH_SHORT).show();
			                    e1.printStackTrace();
			                 }
			                
			                clientThread = new Thread(client);
			                clientThread.start();

			                ClientSerializable clientSer = new ClientSerializable();
			                clientSer.client = client;
			                Intent in = new Intent(ConnectionActivity.this,LoginActivity.class);
			                in.putExtra("clientObject", clientSer);
			                startActivity(in);

			            }
			            catch(Exception ex){
			                Toast.makeText(getApplicationContext(), "Server not found\n", Toast.LENGTH_SHORT).show();
			            }
			        }
			}

		});

	}
	
	public void alert(){
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		    alertDialogBuilder.setMessage("Make sure you have start your server.");
		    alertDialogBuilder.setPositiveButton("Ok",
		        new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface arg0, int arg1) {
		        	
		        }
		    });

		    AlertDialog alertDialog = alertDialogBuilder.create();
		    alertDialog.show();
	}
	
	
	@Override
	public void onBackPressed() {
		finish();
		System.exit(0);
	}
	
	protected void onPostExecute(Socket socket) {
        if (socket != null) {
            Toast.makeText(getApplicationContext(), "Connnected With Server", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Can't connect to server!",
                    Toast.LENGTH_LONG).show();
        }

    }

}
