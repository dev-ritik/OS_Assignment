package Dining_philosopher;

import java.util.concurrent.locks.*;

public class State {
    Lock mutex = new ReentrantLock();
    Condition[] conditions = new Condition[5];
    String[] state = new String[5];
    int[] id = new int[5];

    public State() {
        for (int i = 0; i < 5; i++) {
            id[i] = i;
            state[i] = "O";
            conditions[i] = mutex.newCondition();
        }
    }

    public void setState(int id, String s) {
        state[id] = s;
    }

    public void getChopsticks(int id, Chopstick l, Chopstick r) {
        mutex.lock();
        try {
            setState(id, "o");
            while (!l.getAvailability() || !r.getAvailability()) {
                conditions[id].await();
            }
            l.setAvailability(false);
            r.setAvailability(false);
            setState(id, "X");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void releaseChopsticks(int id, Chopstick l, Chopstick r) {
        mutex.lock();
        setState(id, "O");
        l.setAvailability(true);
        r.setAvailability(true);
        conditions[(id + 1) % 5].signalAll();
        conditions[(id + 4) % 5].signalAll();
        mutex.unlock();
    }
}
