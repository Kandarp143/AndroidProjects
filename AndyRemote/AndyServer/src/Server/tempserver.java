/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Parth
 */
public class tempserver extends JFrame
{
    Socket clientSocket;
    ServerSocket serverSocket = null;
    String ipAddress;
    int portNumber;
    DataInputStream dis;
    String command = "";
    String prefix, postfix;
    
    Robot robot;
    static int capsCount;
    static int altCount;
    
    final String shutdownPath = System.getenv("WINDIR") + "\\system32\\shutdown.exe";
    
    final String fileDirectory = System.getenv("userprofile") + "\\Desktop\\Andy-Remote\\";
    tempserver()
    {
        capsCount = 0;
        altCount = 0;
        try 
        {
            serverSocket = new ServerSocket(5000);
            robot = new Robot();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(tempserver.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(AWTException ex)
        {
            ex.printStackTrace();
        }
        
        try
        {
            clientSocket = serverSocket.accept();

            dis = new DataInputStream(clientSocket.getInputStream());

            command = dis.readUTF();

            System.out.println("device " + command);
            Socket tempSocket = new Socket(clientSocket.getInetAddress(), 5020);
            DataOutputStream tempdos = new DataOutputStream(tempSocket.getOutputStream());

            System.out.println("Connected to " + clientSocket.getInetAddress() + " : " + clientSocket.getPort());
            tempdos.writeUTF(System.getProperty("os.name"));
        }
        catch(IOException iox)
        {
            
        }
        
        new Thread()
        {
            public void run()
            {
                
                while(true)
                {
                    try
                    {
                        clientSocket = serverSocket.accept();
                        dis = new DataInputStream(clientSocket.getInputStream());
                        command = dis.readUTF();
                    }
                    catch(IOException ex)
                    {

                    }
                    System.out.println("got command " + command);

                    prefix = command.substring(0,3);
                    postfix = command.substring(3);

                    if(prefix.equals("vlc"))
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                /*
                                 * String line;
                                    try {
                                        Process proc = Runtime.getRuntime().exec("wmic.exe");
                                        BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                                        OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
                                        oStream .write("process where name='explorer.exe'");
                                        oStream .flush();
                                        oStream .close();
                                        while ((line = input.readLine()) != null) {
                                            System.out.println(line);
                                        }
                                        input.close();
                                    } catch (IOException ioe) {
                                        ioe.printStackTrace();
}
                                 */
                                /*try 
                                {
                                    new ProcessBuilder("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe").start();
                                    
                                } 
                                catch (IOException ex)
                                {
                                    Logger.getLogger(tempserver.class.getName()).log(Level.SEVERE, null, ex);
                                }*/
                                vlcControl(postfix);
                            }
                        }.start();
                    }

                    if(prefix.equals("ppt"))
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                System.out.println("ppt postfix" + postfix);
                                int keyCode = Integer.parseInt(postfix);
                                
                                keyPress(keyCode);
                            }
                        }.start();
                    }

                    if(prefix.equals("pow"))
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                powerControl(postfix);
                            }
                        }.start();
                    }
                    
                    if(prefix.equals("wmp"))
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                System.out.println("wmp postfix" + postfix);
                                int keyCode = Integer.parseInt(postfix);
                                
                                wmpControl(keyCode);
                            }
                        }.start();
                    }
                    
                    if(prefix.equals("key"))
                    {
                        new Thread()
                        {
                            public void run()
                            {
                                System.out.println("key postfix" + postfix);
                                int keyCode = Integer.parseInt(postfix);
                                
                                keyPress(keyCode);
                            }
                        }.start();
                    }
                    
                    if(prefix.equals("fil"))
                    {
                        System.out.println("file postfix" + postfix);
                        String fileName = postfix;
                        byte[] byteArray;
                        int current;
                        int bytesRead;
                        
                        File f;
                        InputStream is;
                        FileOutputStream fos;
                        BufferedOutputStream bos;
                        try 
                        {
                            long fileSize = dis.readLong();
                            System.out.println("filesize" + fileSize);
                            byteArray = new byte[(int)fileSize];
                            float mb = (fileSize / (1024 * 1024));
                            
                            is = clientSocket.getInputStream();
                            bytesRead = is.read(byteArray, 0, byteArray.length);
                            current = bytesRead;
                            
                            File directory = new File(fileDirectory);
                            if(!directory.exists())
                            {
                                directory.mkdir();
                            }
                            
                            f = new File(directory + "\\" + fileName);
                            fos = new FileOutputStream(f);
                            bos = new BufferedOutputStream(fos);
                            
                            do 
                            {
                                bytesRead = is.read(byteArray, current, (byteArray.length-current));
                                // System.out.println("step 2 bytearray :" + byteArray.length + "current : " + current + "differect : " + (byteArray.length - current));
                                int process = current - byteArray.length;
                                System.out.println("diff " + process);
                                System.out.println("PROCESS " + (100 - ((process*100)/byteArray.length)));
                                if(bytesRead >= 0) 
                                {
                                    current += bytesRead;
                                }
                            } while(bytesRead > 0);
                                bos.write(byteArray, 0 , current);
                                bos.flush();

                                bos.close();
                        }
                        catch (IOException ex)
                        {
                            Logger.getLogger(tempserver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
            }
        }.start();
    }
    
    private void keyPress(int keyCode)
    {
        if(keyCode > 1000)
        {
            robot.keyPress(KeyEvent.VK_SHIFT);
            int trim = Integer.parseInt(String.valueOf(keyCode).substring(2));
            System.out.println("key code " + trim);
            robot.keyPress(trim);
            robot.keyRelease(trim);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
        else if(keyCode == 17)
        {
            if(capsCount %2 == 0)
            {
                robot.keyPress(keyCode);
            }
            else
            {
                robot.keyRelease(keyCode);
            }
            capsCount++;
        }
        else if(keyCode == 18)
        {
            if(altCount %2 == 0)
            {
                robot.keyPress(keyCode);
            }
            else
            {
                robot.keyRelease(keyCode);
            }
            altCount++;
        }
        else
        {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            robot.keyRelease(KeyEvent.VK_ALT);
        }
    }
    
    
    private String ipToString(InetAddress ip)
    {
        return ip.toString().replace("/", "");
    }
    
    private void wmpControl(int keyCode)
    {
        if(keyCode < 100)
        {
            robot.keyPress(KeyEvent.VK_CONTROL);
            keyPress(keyCode);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        else
        {
            keyPress(keyCode);
        }
    }
    
    
    
    private void powerControl(String command)
    {
        String[] params = new String[4];
        params[0] = command.substring(0,2);
        params[1] = command.substring(2,4);
        params[2] = command.substring(4,6);
        params[3] = command.substring(6);
        System.out.println("command " + command + "\n");
        for(int i = 0 ; i < 4; i++)
        {
            System.out.println(params[i]);
        }
        try 
        {
            new ProcessBuilder(shutdownPath,params[0], params[1], params[2], params[3]).start();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(tempserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void vlcControl(String command)
    {
        try 
        {
            new ProcessBuilder("src\\Server\\TCPline.exe","localhost", "5678", "1", "A:" + command).start();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(tempserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args) throws IOException 
    {
        new tempserver();
    }
}
