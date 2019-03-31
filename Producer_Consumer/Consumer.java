package Producer_Consumer;

public class Consumer extends Thread {
    String name;
    MySemaphores mutex;
    MySemaphores full;
    MySemaphores empty;
    int[] buffer;

    public Consumer(String name, MySemaphores mutex, MySemaphores full, MySemaphores empty, int[] buffer) {
        this.name = name;
        this.mutex = mutex;
        this.full = full;
        this.empty = empty;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("begin:cons: "+name);
        int times = 4;
        do {
            try {
                full.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                full.release();
                e.printStackTrace();
            }

            int length = buffer[0];
            for (int i = 1; i <= length; i++) {
                System.out.println("buffer cons: " + i + " : " + buffer[i]);
            }

            mutex.release();
            empty.release();
        }while (times-->0);
    }
}
