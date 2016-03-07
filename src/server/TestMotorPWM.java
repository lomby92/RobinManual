package server;

/**
 * Created by lmillucci on 07/03/16.
 */
public class TestMotorPWM {

    public static void main(String args[]) throws Exception{
        MotorPWM motor = MotorPWM.getInstance();

        motor.setMotorsForward();
        motor.setSpeed(100,100);

        Thread.sleep(10000);

        motor.setSpeed(0, 0);
    }
}
