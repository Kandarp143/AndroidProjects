/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;

/**
 *
 * @author SMIT
 */
public class NewClass 
{
    public static void main(String[] args) throws AWTException, InterruptedException, IOException 
    {
      
        
        new ProcessBuilder(System.getenv("WINDIR") + "\\system32\\shutdown.exe", "/s", "/f", "/t", "0").start();
        
        System.out.println("div " + KeyEvent.VK_SLASH + "sub " + KeyEvent.VK_STOP + "equals " + KeyEvent.VK_EQUALS + "fullstop"
                + KeyEvent.VK_PERIOD + "single quote " + KeyEvent.VK_QUOTE + "semicolon " + KeyEvent.VK_SEMICOLON + "square left and right " + KeyEvent.VK_OPEN_BRACKET + " " + 
                KeyEvent.VK_CLOSE_BRACKET + "backslash " + KeyEvent.VK_BACK_SLASH + "tild " + KeyEvent.VK_BACK_QUOTE);
        
        Robot rb = new Robot();
        
        Thread.sleep(4000);
        
        int[] array = new int[]{47, 45 , 61, 46, 44 , 222 , 59, 91, 93, 92, 192};
        for(int i = 0 ; i < array.length ; i++)
        {
            rb.keyPress(array[i]);
            rb.keyRelease(array[i]);
            rb.keyPress(KeyEvent.VK_SPACE);
            rb.keyRelease(KeyEvent.VK_SPACE);
        }
        
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        for(int i = 0 ; i < array.length ; i++)
        {
            rb.keyPress(KeyEvent.VK_SHIFT);
            rb.keyPress(array[i]);
            rb.keyRelease(array[i]);
            rb.keyRelease(KeyEvent.VK_SHIFT);
            rb.keyPress(KeyEvent.VK_SPACE);
            rb.keyRelease(KeyEvent.VK_SPACE);
        }
        
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        int start = 48;
        
        for(int i = 0 ; i < 10 ; i++)
        {
            rb.keyPress(start);
            rb.keyRelease(start);
            rb.keyPress(KeyEvent.VK_SPACE);
            rb.keyRelease(KeyEvent.VK_SPACE);
            start++;
        }
        
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        start = 48;
        for(int i = 0 ; i < 10 ; i++)
        {
            rb.keyPress(KeyEvent.VK_SHIFT);
            rb.keyPress(start);
            rb.keyRelease(start);
            rb.keyRelease(KeyEvent.VK_SHIFT);
            rb.keyPress(KeyEvent.VK_SPACE);
            rb.keyRelease(KeyEvent.VK_SPACE);
            start++;
        }
    }
}
