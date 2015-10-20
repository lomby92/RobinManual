import java.net.Socket;

/**
 * Created by lmillucci on 20/10/15.
 */
public class Controller {

    public static  void main(String[] args){
        Connection server = new Connection();
        server.start();
        //Socket client = server.waitConnection();
//        while (true){
//            if(!client.isConnected()){
//                System.out.println("sono nell'if");
//                client = server.waitConnection();
//
//            }
//            String msg = server.getMessage();
//            if(msg.equals("END")){
//                server.disconnect();
//            }
//            System.out.println(msg);
//        }
    }
}
