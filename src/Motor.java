/**
 * Created by lmillucci on 20/10/15.
 */
public class Motor extends Thread {

    private int actualVel1 = 0;
    private int actualVel2 = 0;

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

            actualVel1 = getLimitedVel(desVel1, actualVel1);
            actualVel2 = getLimitedVel(desVel2, actualVel2);

            System.out.println(actualVel1 + " - " + actualVel2);

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

    private int getLimitedVel(int des, int actual){
        if(actual < des){
            //accelero
            actual = actual +30;
            actual = Math.min(actual, 100);
            actual = Math.min(actual, des);

        }else{
            //rallento
            actual = actual -30;
            actual = Math.max(-100, actual);
            actual = Math.max(actual, des);
        }
        return actual;
    }

}
