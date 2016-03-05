package server;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Singleton che gestisce il collegamento fisico del motore
 *
 * Created by lmillucci on 05/03/16.
 */

public class MotorPWM {

    private final static int PIN_NUMBER1 = 1;
    private final static int PIN_NUMBER2 = 2;

    private static MotorPWM instance = new MotorPWM();

    private MotorPWM(){
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(PIN_NUMBER1, 0, 100);
        SoftPwm.softPwmCreate(PIN_NUMBER2, 0, 100);
    }


    public void setSpeed(int speed1, int speed2) throws InterruptedException {
        SoftPwm.softPwmWrite(PIN_NUMBER1, speed1);
        SoftPwm.softPwmWrite(PIN_NUMBER2, speed2);
    }
}
