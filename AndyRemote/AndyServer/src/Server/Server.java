/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author SMIT
 */
public class Server extends JFrame
{
    JLabel lbl_ip, lbl_port, lbl_status;
    Color blue;
    ServerSocket serversocket;
    Socket clientsocket;
    static int port = 5000;
    DataInputStream dis;
    DataOutputStream dos;
    String mobile;
    final String client_string = "Client Connected : ";
    String command, prefix, postfix;
    Robot robot;
   // Thread listen;
    Server() throws SocketException
    {
        //serversocket = new ServerSocket(5000);
        checkPort();
        clientsocket = null;
        dis = null;
        dos = null;
        mobile = "";
        try 
        {
            robot = new Robot();
        } 
        catch (AWTException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setTitle("Andy - Server");
        this.setSize(600,300);
        this.setResizable(false);
        
        blue = new Color(808080);
        lbl_ip = new JLabel();
        lbl_port = new JLabel("Port : " + port);
        lbl_status = new JLabel("Client Connected : none");
        lbl_ip.setForeground(blue);
        lbl_ip.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        lbl_port.setForeground(blue);
        lbl_port.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        lbl_status.setForeground(blue);
        lbl_status.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        
        this.setLayout(new FlowLayout());
        this.getContentPane().add(lbl_ip);
        this.getContentPane().add(lbl_port);
        this.getContentPane().add(lbl_status);
        
        checkIP();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        Thread startServerThread =  new Thread()
        {
          public void run()
          {
              startServer();
          }
        };
        
        command = "";
        startServerThread.start();
        
        
        
        
        //listen.start();
   }
    
    Thread listen = new Thread()
        {
            public void run()
            {
                while(true)
                {
                try {
                    clientsocket = serversocket.accept();
                    DataInputStream temmpdos = new DataInputStream(clientsocket.getInputStream());
                    System.out.println("reve " + temmpdos.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        };
    
    
    
    private void startServer()
    {
            try 
            {
                clientsocket = serversocket.accept();
                dis = new DataInputStream(clientsocket.getInputStream());
                dos = new DataOutputStream(clientsocket.getOutputStream());
                
                mobile = dis.readUTF();
                System.out.println(mobile);
                lbl_status.setText(client_string + mobile);
                
               // System.out.println(System.getProperties());
               // System.out.println(System.getProperty("os.name"));
                System.out.println(clientsocket.getInetAddress() + " " + clientsocket.getPort());

                
                Socket temp_socket = new Socket(clientsocket.getInetAddress(), 5020);
                
                dos = new DataOutputStream(temp_socket.getOutputStream());
                
                dos.writeUTF(System.getProperty("os.name"));
                
                //dos.writeUTF(System.getProperty("os.name"));
                
            }
            catch (IOException ex)
            {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        
           // listen.start();
            
            
            while(true)
            {
                try 
                {
                  clientsocket = serversocket.accept();
                  
                  command = dis.readUTF();
                  prefix = command.substring(0, 3);
                  System.out.println("prefix" + prefix);
                  postfix = command.substring(3);
                  System.out.println("postfix" + postfix);
                  if("vlc".equals(prefix))
                  {
                      new Thread()
                      {
                        public void run()
                        {
                            try 
                            {
                                Process p = new ProcessBuilder("src\\Server\\TCPline.exe","localhost", "5678", "1", "A:" + postfix).start();
                            } 
                            catch (IOException ex) 
                            {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                        }
                      }.start();
                  }
                  
                  if("ppt".equals(prefix))
                  {
                      new Thread()
                      {
                          public void run()
                          {
                              if("start".equals(postfix))
                              {
                                  keyPress(KeyEvent.VK_F5);
                              }
                              if("stop".equals(postfix))
                              {
                                  keyPress(KeyEvent.VK_ESCAPE);
                              }
                              if("next".equals(postfix))
                              {
                                  keyPress(KeyEvent.VK_RIGHT);
                              }
                              if("prev".equals(postfix))
                              {
                                  keyPress(KeyEvent.VK_LEFT);
                              }
                          }
                      }.start();
                  }
                  
                  
                  
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    }
    
    
    private void keyPress(int code)
    {
        robot.keyPress(code);
        robot.keyRelease(code);
    }
    
    
    
    private String ipToString(InetAddress ip)
    {
        return ip.toString().replace("/", "");
    }
    
    
    private void checkIP() throws SocketException 
    {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) 
        {
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) 
            {
                if(inetAddress.toString().replace("/", "").startsWith("192") || inetAddress.toString().replace("/", "").startsWith("169") || inetAddress.toString().replace("/", "").startsWith("10"))
                {
                    lbl_ip.setText("IP : " + ipToString(inetAddress));
                }
            }
        }
    }
    
    private void checkPort()
    {
        try {
            serversocket = new ServerSocket(port);
        } catch (IOException ex) {
            //ex.printStackTrace();
            port++;
            checkPort();
        }
    }
    
    
    public static void main(String[] args) throws SocketException 
    {
        new Server();
    }
    
}
