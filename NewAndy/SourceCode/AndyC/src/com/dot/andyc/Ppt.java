package com.dot.andyc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Ppt extends Activity {
	@SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();

	// check login
	String ipAddress;
	int portNumber;
	// xml data
	Button start, stop, next, previous;
	// key code
	final int startKeyCode = 116, stopKeyCode = 27, nextKeyCode = 39,
			previousKeyCode = 37;
	// identify ppt
	final String pptPrefix = "ppt";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.setContentView(R.layout.ppt);
		StrictMode.setThreadPolicy(policy);

		start = (Button) findViewById(R.id.ppt_play);
		stop = (Button) findViewById(R.id.ppt_stop);
		next = (Button) findViewById(R.id.ppt_next);
		previous = (Button) findViewById(R.id.ppt_preveous);

		ipAddress = Login.ipAddress;
		portNumber = Login.portNumber;

		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(startKeyCode);
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(stopKeyCode);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(nextKeyCode);
			}
		});

		previous.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(previousKeyCode);
			}
		});

	}

	private void sendCommand(int keyCode) {
		Socket pptSocket;
		DataOutputStream pptDos;

		try {
			pptSocket = new Socket(ipAddress, portNumber);
			pptDos = new DataOutputStream(pptSocket.getOutputStream());

			pptDos.writeUTF(pptPrefix + keyCode);
			pptDos.flush();

			// pptDos.writeInt(keyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
