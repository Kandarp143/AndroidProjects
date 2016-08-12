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
public class Power extends Activity {
	@SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();

	Button shutdown, restart, logoff, hibernate, sleep;

	Socket powerSocket;
	DataOutputStream powerDos;

	String shutdownCommand, restartCommand, hibernateCommand, sleepCommand,
			logoffCommand;

	final String powerPrefix = "pow";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.power);
		StrictMode.setThreadPolicy(policy);

		shutdown = (Button) findViewById(R.id.power_shutdown);
		restart = (Button) findViewById(R.id.power_restart);
		logoff = (Button) findViewById(R.id.power_logoff);
		hibernate = (Button) findViewById(R.id.power_hibernet);
		sleep = (Button) findViewById(R.id.power_sleep);

		try {
			powerSocket = new Socket(Login.ipAddress, Login.portNumber);
			powerDos = new DataOutputStream(powerSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		shutdownCommand = "shutdown -s";
		restartCommand = "shutdown -r";
		hibernateCommand = "shutdown -h";
		logoffCommand = "shutdown -l";
		sleepCommand = "Rundll32.exe powrprof.dll,SetSuspendState Sleep";

		shutdown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(shutdownCommand);
			}
		});

		restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(restartCommand);
			}
		});

		logoff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(logoffCommand);
			}
		});

		hibernate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(hibernateCommand);
			}
		});

		sleep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendCommand(sleepCommand);
			}
		});

	}

	private void sendCommand(String command) {
		try {
			powerDos.writeUTF(powerPrefix + command);
			powerDos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
