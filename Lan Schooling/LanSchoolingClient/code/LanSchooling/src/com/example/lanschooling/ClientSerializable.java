package com.example.lanschooling;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import com.socket.SocketClient;

public class ClientSerializable implements Serializable{
	
	/*Global Variable and Methods and Strings are Declared Here */ 
	
	private static final long serialVersionUID = 1L;
	
	public static SocketClient client;
	
	public static ArrayList<UserDetails> LoginUserName = new ArrayList<UserDetails>();
	public static ArrayList<UserChat> allObjectArray = new ArrayList<UserChat>();
	
	public static String unmLogin = "";
	public static String globalMsgSender = "";
	public static String globalMsgSenderTime = "";
	public static String senderName = "";
	public static String signoutUser = "";
	public static String selectedChatUser = "";
	public static String currentChatUserName = "";
	public static String filePath = "";
	public static Boolean dialogBoxStatus =false;
	public static File uploadFile = null;
	public static String msgContent = "";
	public static String absolutefilePath = "";
	public static Boolean isRead = false;

}
