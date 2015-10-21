import java.net.Socket;

/**
 * Created by lmillucci on 20/10/15.
 */
public class Controller {

    public static  void main(String[] args){
        Connection server = new Connection();
        //server.start();

        String msg = "";
        while (true){
            server.waitConnection();
            msg = "";
            while(msg!=null && !msg.equals("end")) {
                msg = server.getMessage();
                System.out.println(msg);
                if( msg!= null){
                    String tmp[]  = msg.split(";");
                    int vel1 = Integer.parseInt(tmp[0]);
                    int vel2 = Integer.parseInt(tmp[1]);
                    int[] v = {vel1, vel2};
                    Status.getInstance().setVel(v);
                }

            }
            server.disconnect();
        }
    }
}
