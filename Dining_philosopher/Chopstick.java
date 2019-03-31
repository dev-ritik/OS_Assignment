package Dining_philosopher;

public class Chopstick {
    private boolean availability;

    public Chopstick() {
        availability = true;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean flag) {
        availability = flag;
    }
}
