package Dining_philosopher;

public class Philosopher implements Runnable {
    State hlp;
    Chopstick l, r;
    int id;

    public Philosopher(int id, Chopstick l, Chopstick r, State i) {
        this.hlp = i;
        this.l = l;
        this.r = r;
        this.id = id;
    }

    public void run() {
        while (true) {
            hlp.getChopsticks(id, l, r);
            try {
                Thread.sleep(1000);
                System.out.println(id + " Eat");
            } catch (Exception e) {
            }
            hlp.releaseChopsticks(id, l, r);
            try {
                Thread.sleep(1000);
                System.out.println(id + " think");
            } catch (Exception e) {
            }
        }
    }
}