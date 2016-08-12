/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author prince
 */
public class client
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(4567);
        System.out.println("started");
        Socket socket = serverSocket.accept();
        System.out.println("conncted " + socket);
        
        File f = new File("C:\\Users\\ladan_000\\Downloads\\Compressed\\file.rar");
        
        byte[] byteArray = new byte[(int)f.length()];
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(byteArray, 0, byteArray.length);
        OutputStream os = socket.getOutputStream();
        System.out.println("before write");
        os.write(byteArray, 0, byteArray.length);
        os.flush();
        System.out.println("sent");
        socket.close();
    }
}
