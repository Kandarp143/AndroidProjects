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
public class Wmp extends Activity 
{
	@SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	

	// XML Data
	Button play, next, previous, stop, volup, voldown, volume;
	// For Msg code
	int playKey, nextKey, prevKey, stopKey, volupKey, voldownKey, volumeKey;
	// Identify command for Wmp
	final String wmpPrefix = "wmp";
	// check login
	String ipAddress;
	int portNumber;


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.setContentView(R.layout.wmp);
		
		StrictMode.setThreadPolicy(policy);
		
		ipAddress = Login.ipAddress;
		portNumber = Login.portNumber;

		playKey = 80;
		stopKey = 83;
		nextKey = 70;
		prevKey = 66;
		volumeKey = 118;
		volupKey = 120;
		voldownKey = 119;

		play = (Button) findViewById(R.id.wmp_play_pause);
		stop = (Button) findViewById(R.id.wmp_stop);
		next = (Button) findViewById(R.id.wmp_next);
		previous = (Button) findViewById(R.id.wmp_previous);
		volup = (Button) findViewById(R.id.wmp_plus);
		voldown = (Button) findViewById(R.id.wmp_minus);
		volume = (Button) findViewById(R.id.wmp_volume);

		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(playKey);
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(stopKey);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(nextKey);
			}
		});

		previous.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(prevKey);
			}
		});

		volume.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(volumeKey);
			}
		});

		volup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(volupKey);
			}
		});

		voldown.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				sendCommand(voldownKey);
			}
		});

	}

	private void sendCommand(int keyCode) {
		Socket wmpSocket;
		DataOutputStream wmpDos;

		try {
			wmpSocket = new Socket(ipAddress, portNumber);
			wmpDos = new DataOutputStream(wmpSocket.getOutputStream());

			wmpDos.writeUTF(wmpPrefix + keyCode);
			wmpDos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
