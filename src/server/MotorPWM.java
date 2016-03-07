package server;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Singleton che gestisce il collegamento fisico del motore
 *
 * Created by lmillucci on 05/03/16.
 */

public class MotorPWM {

    private final static int PWM1_PIN = 1;
    private final static int DIR_A_PIN1 = 3;
    private final static int DIR_B_PIN1 = 4;

    private final static int DIR_A_PIN2 = 5;
    private final static int DIR_B_PIN2 = 6;
    private final static int PIN_NUMBER2 = 2;



    private static MotorPWM instance = new MotorPWM();

    private MotorPWM(){
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(PWM1_PIN, 0, 100);
        SoftPwm.softPwmCreate(PIN_NUMBER2, 0, 100);
    }


    public void setSpeed(int speed1, int speed2) throws InterruptedException {
        SoftPwm.softPwmWrite(PWM1_PIN, speed1);
        SoftPwm.softPwmWrite(PIN_NUMBER2, speed2);
    }
}
