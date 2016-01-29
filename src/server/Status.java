package server;

import java.util.Observable;

/**
 * Questa classe rappresenta lo stato del robot.
 * Lo stato e' costituito dalle velocita' dei motori (int) e dall'abilitazione del motore del rullo (boolean)
 *
 * @Author Lorenzo Millucci
 * @Since 29/01/16
 */
public class Status extends Observable{

    private int targetMotor1, targetMotor2;
    private static Status status = new Status();
    private boolean rollEnabled;

    private  Status(){
        targetMotor1 = 0;
        targetMotor2 = 0;
        rollEnabled = false;
    }

    public static Status getInstance(){
        return status;
    }

    public synchronized int[] getVel(){
        int[] tmp = new int[2];
        tmp[0] = targetMotor1;
        tmp[1] = targetMotor2;
        return tmp;
    }

    public synchronized void setStatus(int motor1, int motor2, boolean rollEnabled){

        //Controllo che la velocita' non sia minore della minima
        motor1 = Math.max(motor1, -100);
        motor2 = Math.max(motor2, -100);
        //Controllo che la velocita' non sia maggiore della massima
        motor1 = Math.min(motor1, 100);
        motor2 = Math.min(motor2, 100);


        this.targetMotor1 = motor1;
        this.targetMotor2 = motor2;
        this.rollEnabled = rollEnabled;


        //Ho appena aggiornato lo stato e quindi lo notifico agli osservatori
        setChanged();
        notifyObservers();
    }


}
