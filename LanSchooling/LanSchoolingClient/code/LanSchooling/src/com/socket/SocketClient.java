package com.socket;

import com.example.lanschooling.ClientSerializable;
import com.example.lanschooling.ConnectionActivity;
import com.example.lanschooling.LoginActivity;
import com.example.lanschooling.MessageActivity;
import com.example.lanschooling.UserChat;
import com.example.lanschooling.UserDetails;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public ConnectionActivity ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public PrintWriter printwriter;
    public ClientSerializable clientModel;
    public MessageActivity mess;
    
    // To connect client with server
    public void connectionMethod(String serverAddr , Integer port ) throws UnknownHostException,IOException,SocketException{

         try 
         {
        	 if(socket == null){
        		 clientModel = new ClientSerializable();
         	     socket = new Socket(serverAddr, port); // connect to the server
        	 }   
        	 
        	 if (socket != null) {
        		 System.out.println("Conncted With Server");

             } else {
            	 System.out.println("Can't connect to server!");
             }
 		    
		 } 
         catch (UnknownHostException e) 
			{
				System.out.println("Server not found unknown");
				e.printStackTrace();
			} 
         catch (IOException e) 
			{
				System.out.println("Server not found unknown");
				e.printStackTrace();
			}
         
         Out = new ObjectOutputStream(socket.getOutputStream());
 	     Out.flush();
		 In = new ObjectInputStream(socket.getInputStream());
    }

    // Runnable thread to keep server in Listening mode
	@Override
	public void run() {
		 boolean keepRunning = true;
	        while(keepRunning){
	            try {
	            	
	            	/* any of the operation like login , 
	            	 signup will pass to the server by message method
	            	 declared in MessaggeActivity Class*/
	            	
	                Message msg = (Message) In.readObject();
	                String msgTime = (new Date()).toString();
	                
	                Date now = new Date();
					String format1 = new SimpleDateFormat("hh:mm aa").format(now);	
					
	                //When any message come from any user or server or send message to them 	                
	                if(msg.type.equals("message")){
	                	 System.out.println("message");
	                	 
	                	 mess = MessageActivity.getInstance();
	                	 if(msg.recipient.equals(ClientSerializable.unmLogin)){
	                		  ClientSerializable.globalMsgSender = msg.sender +" > " + msg.content +" ("+format1+") "+ "\n";
	                		  ClientSerializable.globalMsgSenderTime= msgTime;
	                		  if(msg.sender.equalsIgnoreCase("SERVER"))
	                		  {
	                			  ClientSerializable.senderName="All";//msg.sender;
	                		  }
	                		  else
	                		  {
	                			  ClientSerializable.senderName=msg.sender;
	                		  }
	                     }
	                     else{
	                    	  ClientSerializable.globalMsgSender = msg.sender +" > " + msg.content +" ("+format1+") "+ "\n";
	                          ClientSerializable.globalMsgSenderTime = msgTime;
	                          ClientSerializable.senderName=msg.recipient;
	                     }
	                	 
	                	 if(mess!=null)
	                	 {
	                		 runableMethod("Message");
	                	 }
	                	 else
	                	 {
	                		 final UserChat userChatObject = new UserChat();
	    					 userChatObject.setName(ClientSerializable.senderName);
	    					 userChatObject.setChatmessage(ClientSerializable.globalMsgSender);
	    					 userChatObject.setIsRead(ClientSerializable.isRead);
	    					 ClientSerializable.allObjectArray.add(userChatObject);
					 
	    					 chatCount();
	                	 }
	                }
	                
	                // When user login
	                else if(msg.type.equals("login")){
	                	 
	                	System.out.println("login");
	                	 
	                	 final LoginActivity loginObj = LoginActivity.getInstance();
	                	 final String contentState = msg.content;
	                	 loginObj.runOnUiThread(new Runnable() {
	 							
	 							@Override
	 							public void run() {
	 							
	 								if(contentState.equals("TRUE")){                		 
	 			                		 loginObj.authenticationLogin("TRUE");
	 			                		 UserDetails userdetail = new UserDetails();
	 			                		 userdetail.setUsername("All");
	 			                		 userdetail.setChatcount("");
	 			                		ClientSerializable.LoginUserName.add(userdetail);
	 			                     }
	 			                     else{
	 			                    	 loginObj.authenticationLogin("FALSE");
	 			                     }
	 							}
	 						 });
                	 
	                }
	                
	                // this is for only test purpose
	                else if(msg.type.equals("test")){
	                	
	                	 System.out.println("test");
	                	 
	                }
	                
	                // when any new user connect with server
	                else if(msg.type.equals("newuser")){
	                	
                	 if(!msg.content.equals(ClientSerializable.unmLogin)){
	                	 boolean exists = false;
	                	 for(int i = 0; i < clientModel.LoginUserName.size(); i++){
                             if(clientModel.LoginUserName.get(i).getUsername().equals(msg.content)){
                                 exists = true; break;
                             }
                         }
	                	 
	                	 if(!exists)
	                	 {
	                		     UserDetails userdetail = new UserDetails();
		                		 userdetail.setUsername(msg.content);
		                		 userdetail.setChatcount("");
	                		     clientModel.LoginUserName.add(userdetail);
	                	 }
                	 }              	 
	                	 
	                }
	                
	                //when user signup
	                else if(msg.type.equals("signup")){
	                	
	                	  System.out.println("signup");
	                	  
	                	     final LoginActivity loginObj = LoginActivity.getInstance();
		                	 final String contentState = msg.content;
		                	 loginObj.runOnUiThread(new Runnable() {
		 							
		 							@Override
		 							public void run() {
		 							
		 								if(contentState.equals("TRUE")){                		 
		 			                		 loginObj.authenticationSignUp("TRUE");
		 			                		 UserDetails userdetail = new UserDetails();
		 			                		 userdetail.setUsername("All");
		 			                		 userdetail.setChatcount("");
		 			                		 ClientSerializable.LoginUserName.add(userdetail);
		 			                     }
		 			                     else{
		 			                    	 loginObj.authenticationSignUp("FALSE");
		 			                     }
		 							}
		 						 });
	                	  
	                }
	                
	                //when any other user or you signout
	                else if(msg.type.equals("signout")){
	                	 System.out.println("signout");
	                	 
	                	 ClientSerializable.signoutUser= msg.content;
	                	 
	                	 mess = MessageActivity.getInstance();
	                	 if(mess!=null)
	                	 {
		                	 if(msg.content.equals(ClientSerializable.unmLogin)){
		                		 runableMethod("signout_me");
		                	 }
		                	 else
		                	 {
		                		 if(ClientSerializable.selectedChatUser.equalsIgnoreCase(ClientSerializable.signoutUser))
		                		 {
			 	                	  ClientSerializable.globalMsgSender = ClientSerializable.selectedChatUser +" > : Bye "+" ("+format1+") "+"\n";
			                		  ClientSerializable.globalMsgSenderTime= msgTime;
			                		  ClientSerializable.senderName=ClientSerializable.selectedChatUser;
			                		  
			                		  runableMethod("signout");
		                		 }
		                		 else
		                		 {
		                			 runableMethod("signout_other");
		                		 }
		                		 
		                	 }
	                	 }
	                	 else
	                	 {
	                		 for (int i = 0; i < ClientSerializable.LoginUserName.size(); i++) {
									UserDetails userName  = ClientSerializable.LoginUserName.get(i);
									if(userName.getUsername().equalsIgnoreCase(ClientSerializable.signoutUser))
									{
										ClientSerializable.LoginUserName.remove(i);
									}
								}
	                	 }
	                	 

	                }
	                
	                // For File Transfer Upload Request
	                else if(msg.type.equals("upload_req")){	                    
	                	 System.out.println("upload_req");
	                	
	                	 
	                	 ClientSerializable.globalMsgSender = msg.sender +" > " + " File Download Complete." +" ("+format1+") "+ "\n";
               		  	ClientSerializable.senderName=ClientSerializable.selectedChatUser;
               		  
	                	 mess = MessageActivity.getInstance();
	                	ClientSerializable.msgContent = msg.content;
	                	runableMethod("upload_req");
	                	
	                }
	                
	                // For File Transfer Upload Response
	                else if(msg.type.equals("upload_res")){
	                	 System.out.println("upload_res");
	                	 
	                	 ClientSerializable.globalMsgSender = ClientSerializable.unmLogin +" > " + " File Upload Complete." +" ("+format1+") "+ "\n";
	               		 ClientSerializable.senderName=ClientSerializable.selectedChatUser;
	               		  	
	                	 if(!msg.content.equals("NO")){
	                        
	                		 if(!msg.content.equals("NO")){
	                             int port  = Integer.parseInt(msg.content);
	                             String addr = msg.sender;
	                             File file = new File(ClientSerializable.absolutefilePath);
	                             Upload upl = new Upload(addr, port, file);
	                             Thread t = new Thread(upl);
	                             t.start();
	                         }
	                     }
	                     else{
	                    	 
	                    	 ClientSerializable.globalMsgSender = ClientSerializable.selectedChatUser +" > " + " rejected file request" +" ("+format1+") "+ "\n";
	                		  	ClientSerializable.senderName=ClientSerializable.selectedChatUser;
	                		  	
	                    	 mess = MessageActivity.getInstance();
	 	                	ClientSerializable.msgContent = msg.content;
	 	                	runableMethod("message");
	                        System.out.println("[SERVER > Me] : "+msg.sender+" rejected file request\n");
	                     }
	                	 
	                 }
	                
	                //When message is not like any above type
	                 else{
	                     System.out.println(" Unknown message type\n");
	                 }
	             }
	                
	                
	            // when any socket problem comes or in casxe of connection lose
	            catch(Exception ex) {
	                keepRunning = false;
	                
	                System.out.println("Exception SocketClient run()");
	                ex.printStackTrace();

	            }
	        }
	}
	
	// Method call for notification of unread message

	public void runableMethod(final String userAction){
		mess.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
				
					 
					 final UserChat userChatObject = new UserChat();
					 userChatObject.setName(ClientSerializable.senderName);
					 userChatObject.setChatmessage(ClientSerializable.globalMsgSender);
					 
					 if(userAction.equalsIgnoreCase("message"))
					 {
						 mess.addTextView();
						 if(ClientSerializable.currentChatUserName.equals(ClientSerializable.senderName))
						 {
							 userChatObject.setIsRead(!ClientSerializable.isRead);
							 
						 }
						 else
						 {
							 userChatObject.setIsRead(ClientSerializable.isRead);
						 }
						 ClientSerializable.allObjectArray.add(userChatObject);
					 }
					 else if(userAction.equalsIgnoreCase("upload_req")){
						 mess.fileUploadReq();
					 }
					 else
					 {
						 if(userAction.equalsIgnoreCase("signout_me"))
						 {
							 mess.closeConncetion(); 
						 }
						 else if(userAction.equalsIgnoreCase("signout"))
						 {
							 mess.addTextView();
							 for (int i = 0; i < ClientSerializable.LoginUserName.size(); i++) {
								 UserDetails userName  = ClientSerializable.LoginUserName.get(i);
									if(userName.getUsername().equalsIgnoreCase(ClientSerializable.signoutUser))
									{
										ClientSerializable.LoginUserName.remove(i);
									}
								}
						 }
						 else
						 {
							 for (int i = 0; i < ClientSerializable.LoginUserName.size(); i++) {
								 UserDetails userName  = ClientSerializable.LoginUserName.get(i);
									if(userName.getUsername().equalsIgnoreCase(ClientSerializable.signoutUser))
									{
										ClientSerializable.LoginUserName.remove(i);
									}
								}
						 }
					 }
					 
				}
			 });
	}
	
	// Method for send message
    
    public void send(Message msg){
        try {
      	
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void downloadComplete(){
    	mess = MessageActivity.getInstance();
    	runableMethod("message");
    }
    
    public void uploadComplete(){
    	mess = MessageActivity.getInstance();
    	runableMethod("message");
    }
    // Method for making count of total unread message
    public void chatCount(){
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
    }
    
 
    // Method For closing the Thread
    public void closeThread(Thread t){
        t = null;
    }

}
