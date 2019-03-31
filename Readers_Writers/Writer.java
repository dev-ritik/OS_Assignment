package Readers_Writers;

public class Writer extends Thread {
    String name;
    MySemaphores mutex;
    MySemaphores wrt;
    MySemaphores empty;
    int[] buffer;

    public Writer(String name, MySemaphores mutex, MySemaphores full, MySemaphores empty, int[] buffer) {
        this.name = name;
        this.mutex = mutex;
        this.wrt = full;
        this.empty = empty;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int times = 0;

        do {
            int time = (int)(Math.random() * 100);
            try {
                sleep(time);
                System.out.println("Writer: "+name+ " waiting");
                wrt.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            wrt.release();
            System.out.println("Wrote: "+name);

        } while(times -->0);

    }
}
