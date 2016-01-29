package client.view;

import client.controller.ClientController;
import client.model.ConnectionManager;
import client.model.Status;

import javax.swing.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by lmillucci on 29/01/16.
 */
public class MainWindow extends JFrame implements ActionListener{
    private JButton connectButton;
    private JTextField ipTextField;
    private JPanel commandPanel;
    private JPanel rootPanel;

    private boolean up, left, right, down;
    private int timer = 0;

    public MainWindow() {
        super("Client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.pack();



        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InetAddress ip = InetAddress.getByName(ipTextField.getText());
                    ClientController.getInstance().startConnection(ip);
                    ClientController.getInstance().startTimer();
                    commandPanel.grabFocus();
                }catch (UnknownHostException ue){
                    System.out.println("Inserito host non valido");
                    ue.printStackTrace();
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ClientController.getInstance().closeMainWindow();
                super.windowClosing(e);
            }
        });

        commandPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyPressed = e.getKeyCode();
                if(keyPressed == e.VK_UP || keyPressed == e.VK_W){
                    up = true;
                }
                if(keyPressed == e.VK_DOWN || keyPressed == e.VK_S){
                    down = true;
                }
                if(keyPressed == e.VK_LEFT || keyPressed == e.VK_A){
                    left = true;
                }
                if(keyPressed == e.VK_RIGHT || keyPressed == e.VK_D){
                    right = true;
                }
                timer++;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyPressed = e.getKeyCode();
                if(keyPressed == e.VK_UP || keyPressed == e.VK_W){
                    up = false;
                }
                if(keyPressed == e.VK_DOWN || keyPressed == e.VK_S){
                    down = false;
                }
                if(keyPressed == e.VK_LEFT || keyPressed == e.VK_A){
                    left = false;
                }
                if(keyPressed == e.VK_RIGHT || keyPressed == e.VK_D){
                    right = false;
                }
                timer=0;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Status.getInstance().isConnected()){
            connectButton.setEnabled(false);
            ClientController.getInstance().calculateVelocityAndSend(up, down, left, right, timer);
        }else{
            connectButton.setEnabled(true);
        }
        System.out.println("Action performed " + timer);

    }
}
