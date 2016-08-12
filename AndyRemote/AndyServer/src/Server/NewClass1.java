/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author prince
 */
public class NewClass1 extends JFrame implements KeyListener
{
JLabel jlbl;
    public NewClass1()
    {
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(jlbl = new JLabel("somehtingf"));
        this.addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("code " + e.getKeyCode());
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        jlbl.setText("key " + e.getKeyChar() + "code " + e.getKeyCode());
        File[] roots = File.listRoots();
        for(int i = 0; i < roots.length ; i++)
            System.out.println("ccc " + roots[i] );
        File temp = roots[0];
        boolean tempd = temp.isDirectory();
        File[] list = temp.listFiles();
        
        for(int i = 0; i < list.length; i++)
        {
            System.out.println("file " + list[i]);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public static void main(String[] args) {
        new NewClass1();
    }
}
