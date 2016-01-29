package server;

import server.filter.DefaultFilter;
import server.filter.IFilter;

import java.util.Observable;
import java.util.Observer;

/**
 * Questa classe gestisce il comportamento dei motori in un Thread separato.
 * Quando viene aggiornato lo stato questa classe viene notificata e aggiorna i target di velocita'.
 * Una volta impostate le velocita' desiderate il motore cerca di raggiungerle tramite un filtro di velocita'
 *
 * Created by lmillucci on 20/10/15.
 */
public class Motor extends Thread  implements Observer{

    private int actualVelocity1 = 0;
    private int actualVelocity2 = 0;
    private boolean isStatusFresh = false;
    private IFilter filterStrategy = new DefaultFilter();
    private int desVel1, desVel2;

    private int timer = 0;

    @Override
    public void update(Observable observable, Object arg){
        isStatusFresh = true;
        int[] desiredVel = Status.getInstance().getVel();

        this.desVel1 = desiredVel[0];
        this.desVel2 = desiredVel[1];

        System.out.println("Lo stato è aggiornato con " + desVel1 + " - " + desVel2);
    }

    public void setFilterStrategy(IFilter filter){
        this.filterStrategy = filter;
    }


    public void run(){

        while (true) {
            if(isStatusFresh){
                timer = 0;
                isStatusFresh = false;
            }


            actualVelocity1 = filterStrategy.filterVelocity(desVel1, actualVelocity1, timer);
            actualVelocity2 = filterStrategy.filterVelocity(desVel2, actualVelocity2, timer);

            System.out.println(actualVelocity1 + " - " + actualVelocity2);

            timer++;

            try {
                Thread.sleep(500);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

        }
    }


}
