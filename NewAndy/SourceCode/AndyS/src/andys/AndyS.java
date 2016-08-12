/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package andys;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Parth
 */
public class AndyS extends JFrame {

    Socket clientSocket;
    ServerSocket serverSocket = null;
    DataInputStream dis;
    DataOutputStream dos;
    String command = "";
    String prefix, postfix;
    Robot robot;
    static int capsCount;
    static int altCount;
    final String shutdownPath = System.getenv("WINDIR") + "\\system32\\shutdown.exe";
    final String fileDirectory = System.getenv("userprofile") + "\\Desktop\\Andy-Remote\\";
    Map<String, String> filePaths;
    static int port = 5000;
    // GUI stuff
    JLabel lbl_ip, lbl_port, lbl_status;
    Color blue;
    final String client_string = "Client Connected : ";

    AndyS() {


        checkPort();

        this.setTitle("Andy - Server");
        this.setSize(600, 300);
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


        try {
            checkIP();
        } catch (SocketException se) {
            System.exit(0);
        }

        capsCount = 0;
        altCount = 0;
        filePaths = new HashMap<String, String>();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        try {
            clientSocket = serverSocket.accept();

            dis = new DataInputStream(clientSocket.getInputStream());

            command = dis.readUTF();

            System.out.println("device " + command);
            lbl_status.setText(client_string + command);
            Socket tempSocket = new Socket(clientSocket.getInetAddress(), 5020);
            DataOutputStream tempdos = new DataOutputStream(tempSocket.getOutputStream());

            System.out.println("Connected to " + clientSocket.getInetAddress() + " : " + clientSocket.getPort());
            tempdos.writeUTF(System.getProperty("os.name"));
        } catch (IOException iox) {
        }

        new Thread() {

            public void run() {

                while (true) {
                    try {
                        clientSocket = serverSocket.accept();
                        dis = new DataInputStream(clientSocket.getInputStream());
                        dos = new DataOutputStream(clientSocket.getOutputStream());
                        command = dis.readUTF();
                    } catch (IOException ex) {
                    }
                    System.out.println("got command " + command);

                    prefix = command.substring(0, 3);
                    postfix = command.substring(3);


                    if (prefix.equals("ppt")) {
                        new Thread() {

                            public void run() {
                                System.out.println("ppt postfix" + postfix);
                                int keyCode = Integer.parseInt(postfix);

                                keyPress(keyCode);
                            }
                        }.start();
                    }

                    if (prefix.equals("pow")) {
                        new Thread() {

                            public void run() {
                                try {
                                    powerControl(postfix);
                                } catch (IOException ex) {
                                    Logger.getLogger(AndyS.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }.start();
                    }

                    if (prefix.equals("wmp")) {
                        new Thread() {

                            public void run() {
                                System.out.println("wmp postfix" + postfix);
                                int keyCode = Integer.parseInt(postfix);

                                wmpControl(keyCode);
                            }
                        }.start();
                    }

                    if (prefix.equals("tou")) {
                        try {
                            int getX = 0, getY = 0;
                            System.out.println("in touch");
                            while ((getX = dis.readInt()) != 32768 && (getY = dis.readInt()) != 32768) {
                                if ((getX == 5000) && (getX == 5000)) {
                                    robot.mousePress(InputEvent.BUTTON1_MASK);
                                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                                } else if ((getX == 5001) && (getX == 5001)) {
                                    robot.mousePress(InputEvent.BUTTON3_MASK);
                                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                } else {
                                    robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x + getX, MouseInfo.getPointerInfo().getLocation().y + getY);

                                }
                                System.out.println("x" + getX + "y" + getY);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AndyS.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }.start();
    }

    private void keyPress(int keyCode) {
        if (keyCode > 1000) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            int trim = Integer.parseInt(String.valueOf(keyCode).substring(2));
            System.out.println("key code " + trim);
            robot.keyPress(trim);
            robot.keyRelease(trim);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (keyCode == 17) {
            if (capsCount % 2 == 0) {
                robot.keyPress(keyCode);
            } else {
                robot.keyRelease(keyCode);
            }
            capsCount++;
        } else if (keyCode == 18) {
            if (altCount % 2 == 0) {
                robot.keyPress(keyCode);
            } else {
                robot.keyRelease(keyCode);
            }
            altCount++;
        } else {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            robot.keyRelease(KeyEvent.VK_ALT);
        }
    }

    private String ipToString(InetAddress ip) {
        return ip.toString().replace("/", "");
    }

    private void wmpControl(int keyCode) {
        if (keyCode < 100) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            keyPress(keyCode);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } else {
            keyPress(keyCode);
        }
    }

    private void powerControl(String command) throws IOException {

        Runtime.getRuntime().exec(command);
    }

    private void checkPort() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            //ex.printStackTrace();
            port++;
            checkPort();
        }
    }

    private void checkIP() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                if (inetAddress.toString().replace("/", "").startsWith("192") || inetAddress.toString().replace("/", "").startsWith("169") || inetAddress.toString().replace("/", "").startsWith("10")) {
                    lbl_ip.setText("IP : " + ipToString(inetAddress));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        new AndyS();
    }
}
