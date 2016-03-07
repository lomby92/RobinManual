package server;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * Singleton che gestisce il collegamento fisico del motore
 *
 * Created by lmillucci on 05/03/16.
 */

public class MotorPWM {

    private final static int PWM1_PIN = 1;
    private final GpioPinDigitalOutput pin_1A;
    private final GpioPinDigitalOutput pin_1B;

    private final static int PWM2_PIN = 2;
    private final GpioPinDigitalOutput pin_2A;
    private final GpioPinDigitalOutput pin_2B;



    private static MotorPWM instance = new MotorPWM();

    private MotorPWM(){
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(PWM1_PIN, 0, 100);
        SoftPwm.softPwmCreate(PWM2_PIN, 0, 100);

        //inizializzo le velocita nulle
        SoftPwm.softPwmWrite(PWM1_PIN, 0);
        SoftPwm.softPwmWrite(PWM2_PIN, 0);
	
	final GpioController gpio = GpioFactory.getInstance();

        pin_1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Motor1A", PinState.LOW);
        pin_1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Motor1B", PinState.HIGH);

        pin_2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Motor2A", PinState.LOW);
        pin_2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Motor2B", PinState.HIGH);



    }

    public void setMotorsForward(){
        //motore 1
        pin_1A.low();
        pin_1B.high();

        //motore 2
        pin_2A.low();
        pin_2B.high();

    }

    public void setMotorsBackward(){
        //motore 1
        pin_1A.high();
        pin_1B.low();

        //motore 2
        pin_2A.high();
        pin_2B.low();

    }



    public void setSpeed(int speed1, int speed2) throws InterruptedException {
        SoftPwm.softPwmWrite(PWM1_PIN, speed1);
        SoftPwm.softPwmWrite(PWM2_PIN, speed2);
    }
}
