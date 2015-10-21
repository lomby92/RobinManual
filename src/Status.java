/**
 * Created by lmillucci on 20/10/15.
 */
public class Status {

    private int targetMotor1, targetMotor2;
    private boolean isFresh;
    private static Status status = new Status();

    private  Status(){
        targetMotor1 = 0;
        targetMotor2 = 0;
        isFresh = true;
    }

    public static Status getInstance(){
        return status;
    }

    public synchronized int[] getVel(){
        int[] tmp = new int[2];
        tmp[0] = targetMotor1;
        tmp[1] = targetMotor2;
        isFresh = false;
        return tmp;
    }

    public synchronized void setVel(int[] vel){

        //Controllo che la velocità non sia minore della minima
        vel[0] = Math.max(vel[0], -100);
        vel[1] = Math.max(vel[1], -100);
        //Controllo che la velocità non sia maggiore della massima
        vel[0] = Math.min(vel[0], 100);
        vel[1] = Math.min(vel[1], 100);


        this.targetMotor1 = vel[0];
        this.targetMotor2 = vel[1];
        isFresh = true;
    }

    public synchronized boolean isFresh(){
        return this.isFresh;
    }

}
