import java.net.Socket;

/**
 * Created by lmillucci on 20/10/15.
 */
public class Controller {

    public static  void main(String[] args){
        Connection server = new Connection();
        Motor motor = new Motor();
        motor.start();

        String msg = "";
        while (true){
            server.waitConnection();
            msg = "";
            while(msg!=null && !msg.equals("end")) {
                msg = server.getMessage();
                //System.out.println(msg);
                if( msg!= null){
                    String tmp[]  = msg.split(";");

                    if(tmp.length == 2){
                        try {
                            int vel1 = Integer.parseInt(tmp[0]);
                            int vel2 = Integer.parseInt(tmp[1]);
                            int[] v = {vel1, vel2};
                            Status.getInstance().setVel(v);
                        }catch (NumberFormatException nfe){
                            System.out.println("Errore nel formato dei dati ricevuti");
                            nfe.printStackTrace();
                        }
                    }

                }

            }
            server.disconnect();
        }
    }
}
