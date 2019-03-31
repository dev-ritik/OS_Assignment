package Sleeping_Barber;

import static Sleeping_Barber.Main.*;

public class Customer extends Thread {

    int iD;
    boolean notCut = true;

    public Customer(int i) {
        iD = i;
    }

    public void run() {
        while (notCut) {
            try {
                accessSeats.acquire();
                if (numberOfFreeSeats > 0) {
                    System.out.println("Customer " + this.iD + " just sat down.");
                    numberOfFreeSeats--;
                    customers.release();
                    accessSeats.release();
                    try {
                        barber.acquire();
                        notCut = false;
                        this.get_haircut();
                    } catch (InterruptedException ex) {
                    }
                } else {
                    System.out.println("There are no free seats. Customer " + this.iD + " has left the barbershop.");
                    accessSeats.release();
                    notCut = false;
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    public void get_haircut() {
        System.out.println("Customer " + this.iD + " is getting his hair cut");
        try {
            sleep(5050);
        } catch (InterruptedException ex) {
        }
    }

}

