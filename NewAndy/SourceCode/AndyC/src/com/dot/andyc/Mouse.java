package com.dot.andyc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Mouse extends Activity {

	// Socket
	Socket touchSocket;
	DataOutputStream touchDos;

	// Pointers
	int finalX, finalY;
	int currentX, currentY;
	int oldX, oldY;
	boolean flag;

	// XML data
	Button leftClick, rightClick;
	View touchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.mouse);

		leftClick = (Button) findViewById(R.id.lc);
		rightClick = (Button) findViewById(R.id.rc);

		try {
			// check connection
			touchSocket = new Socket(Login.ipAddress, Login.portNumber);
			touchDos = new DataOutputStream(touchSocket.getOutputStream());
			touchDos.writeUTF("tou");
			touchDos.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oldX = oldY = 0;

		leftClick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					// send msg in code
					touchDos.writeInt(5000);
					touchDos.writeInt(5000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		rightClick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					touchDos.writeInt(5001);
					touchDos.writeInt(5001);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		touchView = findViewById(R.id.abs);
		touchView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					oldX = (int) event.getRawX();
					oldY = (int) event.getRawY();
					flag = true;
					Log.d("inint oldx", "" + oldX);
					Log.d("init oldy", "" + oldY);
				}

				if (event.getAction() == MotionEvent.ACTION_MOVE) {

					try {
						if (!flag) {
							oldX = currentX;
							oldY = currentY;
						}

						currentX = (int) event.getRawX();
						currentY = (int) event.getRawY();

						finalX = currentX - oldX;

						Log.d("oldx", "" + oldX);
						Log.d("oldy", "" + oldY);

						Log.d("currentx", "" + currentX);
						Log.d("currenty", "" + currentY);

						// finalY = initialY - y;

						finalY = currentY - oldY;

						touchDos.writeInt(finalX);
						// touchDos.flush();
						touchDos.writeInt(finalY);
						touchDos.flush();

						Log.d("send", "diffx" + finalX + "diffy" + finalY);
						flag = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		try {
			touchDos.writeInt(32768);
			touchDos.writeInt(32768);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
