/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author prince
 */
public class server
{
    public static void main(String[] args) throws UnknownHostException, IOException {
        int bytesRead;
        int current = 0;
        int filesize = 6230716;
        
        Socket socket = new Socket(InetAddress.getLocalHost(), 4567);
        System.out.println("Connecting");
        
        byte[] byteArray = new byte[filesize];
        InputStream is = socket.getInputStream();
        File f = new File(System.getenv("userprofile") + "\\Desktop\\Andy-remote\\");
        if(!f.exists())
        {
            f.mkdir();
        }
        FileOutputStream fos = new FileOutputStream(System.getenv("userprofile") + "\\Desktop\\Andy-remote\\getthis.rar");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(byteArray, 0, byteArray.length);
        System.out.println("step 1 - byte length " + byteArray.length + "current " + current);
        current = bytesRead;
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
        System.out.println("get this");
        bos.write(byteArray, 0 , current);
    bos.flush();
    
    bos.close();
    
    socket.close();
    }
}
