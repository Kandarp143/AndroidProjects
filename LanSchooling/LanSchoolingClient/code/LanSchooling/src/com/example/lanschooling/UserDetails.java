package com.example.lanschooling;

public class UserDetails {
	
	// getter -  setter method for notification of unread message and its count
	private String username;
	private String chatcount;
	
	public String getChatcount() {
		return chatcount;
	}
	public void setChatcount(String chatcount) {
		this.chatcount = chatcount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
