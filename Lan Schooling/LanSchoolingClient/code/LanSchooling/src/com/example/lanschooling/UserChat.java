package com.example.lanschooling;

public class UserChat {
	
	// use for globally set chat message and time - POJO Class

	public String name;
	private String chatmessage;
	private String chatTime;
	private Boolean isRead;
	
	
   // constuctor 
	public UserChat() {
		super();
	}

	public Object getObjectValue() {
		return this;
	}
	
	// to get name of user of message sender
	public String getName() {
		return this.name;
	}
	
	// to set name of user of message sender
	public void setName(String name) {
		this.name = name;
	}
	
	// to get chat message from message sender
	public String getChatmessage() {
		return chatmessage;
	}
	
	// to set chat message from message sender
	public void setChatmessage(String chatmessage) {
		this.chatmessage = chatmessage;
	}
	
	// to get value that message has been read or not
	public Boolean getIsRead() {
		return isRead;
	}
	
	// to set value for message read flag
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
	// to get chat time when user has send/receive message
	public String getChatTime() {
		return chatTime;
	}
	
	// to set chat time when user has send/receive message
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
}
