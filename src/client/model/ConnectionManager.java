package client.model;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Classe che gestisce la connessione al server
 * Created by lmillucci on 29/01/16.
 */
public class ConnectionManager {

    private Socket client;
    private BufferedWriter out;
    private final int PORT = 35555;
    private static ConnectionManager instance = new ConnectionManager();

    private ConnectionManager(){};

    public static ConnectionManager getInstance(){
        return instance;
    }

    public void startConnection(InetAddress ip){
        try {
            client = new Socket(ip, PORT);
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
            Status.getInstance().setConnected(true);
        }catch (IOException ioe){
            System.out.println("Errore nella creazione della connessione");
            ioe.printStackTrace();
        }
    }

    public void endConnection(){
        if(Status.getInstance().isConnected()) {
            try {
                out.write("END\r\n");
                out.flush();
                out.close();
                client.close();
                Status.getInstance().setConnected(false);

            } catch (IOException ioe) {
                System.out.println("Errore nella chiusura della connessione");
                ioe.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        try {
            out.write(message + "\r\n");
            out.flush();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
