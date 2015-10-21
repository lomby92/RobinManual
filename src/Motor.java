/**
 * Created by lmillucci on 20/10/15.
 */
public class Motor extends Thread {

    private int actualVelocity1 = 0;
    private int actualVelocity2 = 0;

    private final int[] SOGLIA_UP = {35, 65, 85, 100};
    private final int[] SOGLIA_DOWN = {40, 65, 100};

    int timer = 0;




    public Motor(){

    }

    public void run(){

        while (true) {
            if(Status.getInstance().isFresh()){
                timer = 0;
            }
            int[] desiredVel = Status.getInstance().getVel();

            int desVel1 = desiredVel[0];
            int desVel2 = desiredVel[1];

            actualVelocity1 = getLimitedVel(desVel1, actualVelocity1);
            actualVelocity2 = getLimitedVel(desVel2, actualVelocity2);

            System.out.println(actualVelocity1 + " - " + actualVelocity2);

            if(timer > 5){
                int[] vel = {0, 0};
                Status.getInstance().setVel(vel);
                System.out.println("Azzero");
            }

            timer++;

            try {
                Thread.sleep(500);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

        }
    }

    private int getLimitedVel(int desired, int actual){
        if(actual < desired){
            //accelero
            actual = actual + 30;
            actual = Math.min(actual, 100);
            actual = Math.min(actual, desired);

        }else{
            //rallento
            actual = actual - 30;
            actual = Math.max(-100, actual);
            actual = Math.max(actual, desired);
        }
        return actual;
    }

}
