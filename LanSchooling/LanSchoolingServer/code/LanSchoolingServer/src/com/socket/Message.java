package com.socket;

import java.io.Serializable;

public class Message implements Serializable{
    
    // to send message with its type , sender name , content of message and receiver name
    private static final long serialVersionUID = 1L;
    public String type, sender, content, recipient;
    
    public Message(String type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
}
