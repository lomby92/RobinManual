package server.filter;

/**
 * Created by lmillucci on 29/01/16.
 */
public class LowPassFilter implements IFilter {

    public int filterVelocity(int desiredVel, int actualVel, int timer){
        if(timer > 5){
            actualVel = 0;
        }else if(actualVel < desiredVel){
            //accelero
            actualVel = actualVel + 30;
            actualVel = Math.min(actualVel, 100);
            actualVel = Math.min(actualVel, desiredVel);

        }else{
            //rallento
            actualVel = actualVel - 30;
            actualVel = Math.max(-100, actualVel);
            actualVel = Math.max(actualVel, desiredVel);
        }


        return actualVel;
    }
}
