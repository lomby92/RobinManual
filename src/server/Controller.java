package server;

import server.filter.LowPassFilter;

/**
 * Created by lmillucci on 20/10/15.
 */
public class Controller {

    public static  void main(String[] args){
        Connection server = new Connection();
        Motor motor = new Motor();

        //imposto i parametri necessari al funzionamento del motore
        motor.setFilterStrategy(new LowPassFilter());
        Status.getInstance().addObserver(motor);

        motor.start();

        String msg = "";
        while (true){
            server.waitConnection();
            msg = "";
            while(msg!=null && !msg.toLowerCase().equals("end")) {
                msg = server.getMessage();
                System.out.println(msg);
                if( msg != null){
                    String tmp[]  = msg.split(";");

                    if(tmp.length == 3){
                        try {
                            int vel1 = Integer.parseInt(tmp[0]);
                            int vel2 = Integer.parseInt(tmp[1]);
                            boolean roll = Integer.parseInt(tmp[2]) == 1 ? true : false;
                            Status.getInstance().setStatus(vel1, vel2, roll);

                        }catch (NumberFormatException nfe){
                            System.out.println("Errore nel formato dei dati ricevuti");
                            nfe.printStackTrace();
                        }
                    }else{
                        server.sendMessage("Inviato messaggio non valido!\r\n");
                    }

                }

            }
            server.disconnect();
        }
    }
}
