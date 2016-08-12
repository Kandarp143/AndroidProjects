package com.dot.andyc;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("NewApi")


public class Login extends Activity {

	//Thread Policy
	@SuppressLint("NewApi")
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	// Android Network Access
	private ConnectivityManager connManager;
	private NetworkInfo mWifi;
	private Context context;

	// Socket programming
	public static Socket clientSocket;
	private ServerSocket serverSocket;
	public static DataOutputStream dos;

	// Threads And Progress
	Thread recThread, sendThread, progressThread, timer;
	ProgressDialog connectionDialog;

	// XML data
	private Button connectButton;
	private EditText ipText, portText;

	// Variables
	public static String ipAddress;
	public static int portNumber;
	private static int serverPort = 5020;
	private String serverName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		StrictMode.setThreadPolicy(policy);	

		context = this;

		connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		// Initialize all the objects...

		ipAddress = "";
		portNumber = 0;
		serverName = "";
		clientSocket = null;
		dos = null;
		serverSocket = null;
		connectionDialog = new ProgressDialog(context);

		// Initialize all the xml components...

		ipText = (EditText) findViewById(R.id.login_ip);
		portText = (EditText) findViewById(R.id.login_port);
		connectButton = (Button) findViewById(R.id.login_connect);

		// All Thread bodies...

		recThread = new Thread() {
			public void run() {
				try {
					checkPort(serverPort);
					Socket tempSocket = serverSocket.accept();
					serverName = new DataInputStream(
							tempSocket.getInputStream()).readUTF();

					runOnUiThread(new Runnable() {
						public void run() {
							connectionDialog.hide();
						}
					});
					toastMessage("Connected to : " + serverName);
					timer.interrupt();
					// startActivity(new Intent(MainActivity.this, Home.class));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		recThread.start();

		connectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!ipText.getText().toString().equals("")
						&& !portText.getText().toString().equals("")) {
					ipAddress = ipText.getText().toString();
					try {
						portNumber = Integer.parseInt(portText.getText()
								.toString());

						sendThread = new Thread() {
							public void run() {
								try {
									clientSocket = new Socket(InetAddress
											.getByName(ipAddress), portNumber);
									dos = new DataOutputStream(clientSocket
											.getOutputStream());
									dos.writeUTF(Build.MANUFACTURER + " "
											+ Build.MODEL);
								} catch (IOException e) {
									neutralAlertDialog("Invalid IP or Port",
											"Please make sure you have entered correct IP address and port.");
									e.printStackTrace();
								}

							}
						};

						progressThread = new Thread() {
							public void run() {
								runOnUiThread(new Runnable() {
									public void run() {
										connectionDialog
												.setMessage("Connecting...");
										connectionDialog.setIndeterminate(true);
										connectionDialog.setCancelable(false);
										connectionDialog.show();
									}
								});

							}
						};

						timer = new Thread() {
							public void run() {
								try {
									sleep(5000);
									runOnUiThread(new Runnable() {
										public void run() {
											connectionDialog.hide();
										}
									});

								} catch (InterruptedException e) {
									startActivity(new Intent(Login.this,
											Home.class));
									// e.printStackTrace();
								}
								if (recThread.isAlive())
									neutralAlertDialog(
											"Unable to connect",
											"Unable to connect to server. Make sure you have entered the correct IP and port.");

							}
						};

						sendThread.start();
						progressThread.start();
						timer.start();

					} catch (NumberFormatException nx) {
						neutralAlertDialog("Port missing",
								"Please enter port number.");
					}

				} else {
					neutralAlertDialog("IP or Port missing",
							"Please enter IP andress or Port number");
				}
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		wifiNotAvailableDialog();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		new AlertDialog.Builder(this)
				.setTitle("Are you sure?")
				.setMessage("Are you sure want to exit?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									dos.writeUTF("exit");
									finish();
								} catch (IOException e) {
									// Nothing to do
									finish();
								}
							}
						}).setNegativeButton("No", null).show();
		// finish();
	}

	public boolean isConnectedToWifi() {
		if (mWifi.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	public void checkPort(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception ex) {
			serverPort++;
			checkPort(port);
		}
	}

	public void wifiNotAvailableDialog() {
		if (!isConnectedToWifi()) {
			runOnUiThread(new Runnable() {
				public void run() {
					AlertDialog.Builder wifiDisconnected = new AlertDialog.Builder(
							context);
					wifiDisconnected.setTitle("Wifi unavailable");
					wifiDisconnected
							.setMessage("The device is not connected to any wireless network.");
					wifiDisconnected.setNeutralButton("Ok", null);
					wifiDisconnected.show();
				}
			});
		}
	}

	public void neutralAlertDialog(final String title, final String message) {
		runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder alertDialog = new Builder(context);
				alertDialog.setTitle(title);
				alertDialog.setMessage(message);
				alertDialog.setNeutralButton("Ok", null);
				alertDialog.show();
			}
		});

	}

	public void toastMessage(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

}
