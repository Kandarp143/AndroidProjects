/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author prince
 */
public class NewClass2 
{
    public static void main(String[] args) throws AWTException {
        Robot rb = new Robot();
        rb.keyRelease(KeyEvent.VK_SHIFT);
    }
}
