package Producer_Consumer;

public class Producer extends Thread {
    String name;
    MySemaphores mutex;
    MySemaphores full;
    MySemaphores empty;
    int[] buffer;

    public Producer(String name, MySemaphores mutex, MySemaphores full, MySemaphores empty, int[] buffer) {
        this.name = name;
        this.mutex = mutex;
        this.full = full;
        this.empty = empty;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("begin:pros: "+name);
        int times = 4;

        do {
            int length = (int) (Math.random() * 4) + 1;
            try {
                empty.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                empty.release();
                e.printStackTrace();
            }

            buffer[0] = length;
            for (int i = 1; i <= length; i++) {
                buffer[i] = (int) (Math.random() * 20);
                System.out.println("buffer prod: " + i + " : " + buffer[i]);
            }

            mutex.release();
            full.release();
        } while (times-->0);
    }
}
