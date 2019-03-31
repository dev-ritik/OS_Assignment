package Sleeping_Barber;

import static Sleeping_Barber.Main.*;

public class Barber extends Thread {

    public Barber() {

    }

    public void run() {
        while (true) {
            try {
                customers.acquire();
                accessSeats.release();
                numberOfFreeSeats++;
                barber.release();
                accessSeats.release();
                System.out.println("The barber is cutting hair");
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}