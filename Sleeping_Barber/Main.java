package Sleeping_Barber;

import static java.lang.Thread.sleep;

public class Main {

    public static MySemaphores customers = new MySemaphores(0);
    public static MySemaphores barber = new MySemaphores(0);
    public static MySemaphores accessSeats = new MySemaphores(1);

    public static final int CHAIRS = 5;

    public static int numberOfFreeSeats = CHAIRS;

    public static void main(String args[]) {

        System.out.println("here");
        Barber giovanni = new Barber();
        giovanni.start();

        for (int i = 1; i < 16; i++) {
            Customer aCustomer = new Customer(i);
            aCustomer.start();
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
