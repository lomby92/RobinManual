import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lmillucci on 20/10/15.
 */
public class Connection {

    private ServerSocket serverSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Socket client;
    private final int PORT = 35555;

    public Connection(){
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server pronto!");
        }catch (IOException ioe){
            System.out.println("Errore nell'avvio del server");
            ioe.printStackTrace();
        }
    }

    public void waitConnection(){
        try {
            client = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
            System.out.println("Connessione effettuata!");
            sendMessage("Connessione stabilita\r\n");
        }catch (IOException ioe){
            System.out.println("Errore nello stabilire la connessione");
            ioe.printStackTrace();

        }
    }

    public String getMessage(){
        String message = "";
        try {
            message = in.readLine();
        }catch (IOException ioe){
            ioe.printStackTrace();
            message = "Exception";
            try{
                client.close();
                in.close();
                out.close();
            }catch (IOException a){
                a.printStackTrace();
            }
        }

        return  message;
    }

    public void sendMessage(String message){
        try{
            out.write(message);
            out.flush();
        }catch (IOException ioe){
            System.out.println("Errore nell'invio del messaggio!");
            ioe.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            client.close();
            in.close();
            out.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void start(){
        Motor m = new Motor();
        m.start();

        try{
            String msg;
            while (true){
                client = serverSocket.accept();
                System.out.println("client connesso");
                in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));

                msg = "";
                while(msg!=null && !msg.equals("end")) {
                    msg = getMessage();
                    if( msg!= null){
                        String tmp[]  = msg.split(";");
                        System.out.println(tmp.length);
                        if(tmp.length != 2){
                            throw new IllegalArgumentException("Inserito messaggio non valido");
                        }
                        int vel1 = Integer.parseInt(tmp[0]);
                        int vel2 = Integer.parseInt(tmp[1]);
                        int[] v = {vel1, vel2};
                        Status.getInstance().setVel(v);
                    }
                    System.out.println(msg);
                }
                out.close();
                in.close();

            }
        }catch (IOException ioe){
            try {
                in.close();
                out.close();
            }catch (IOException i){
                i.printStackTrace();
            }
        }catch (NullPointerException ne){
            ne.printStackTrace();
        }
    }

}
