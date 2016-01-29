package client.controller;

import client.model.ConnectionManager;
import client.model.filter.DefaultFilter;
import client.model.filter.IFilter;
import client.view.MainWindow;

import javax.swing.*;
import java.net.InetAddress;

/**
 * Created by lmillucci on 29/01/16.
 */
public class ClientController {

    private static ClientController instance = new ClientController();
    private MainWindow mainWindow;
    private Timer timer;
    private IFilter filter = new DefaultFilter();
    private String messageOld;

    private ClientController(){
        mainWindow = new MainWindow();
        timer = new Timer(100, mainWindow);
    };

    public static ClientController getInstance(){
        return instance;
    }

    public void setFilter(IFilter f){
        this.filter = f;
    }

    public void openMainWindow(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
            }
        });
    }

    public void closeMainWindow(){
        ConnectionManager.getInstance().endConnection();
    }

    public void startConnection(InetAddress ip){
        ConnectionManager.getInstance().startConnection(ip);
    }

    public void startTimer(){
        if(timer != null){
            timer.start();
        }
    }

    public void stopTimer(){
        if(timer != null){
            timer.stop();
        }
    }

    public void calculateVelocityAndSend(boolean up, boolean down, boolean left, boolean right, int time){
        int[] vel = filter.filter(up, down, left, right, time);
        String message = vel[0] + ";" + vel[1] + ";" + "0";
        if(!message.equals(messageOld)) {
            ConnectionManager.getInstance().sendMessage(message);
            messageOld = message;
        }
    }

}
