package Readers_Writers;

public class Reader extends Thread {
    String name;
    MySemaphores mutex;
    MySemaphores wrt;
    MySemaphores empty;
    int[] buffer;

    public Reader(String name, MySemaphores mutex, MySemaphores full, MySemaphores empty, int[] buffer) {
        this.name = name;
        this.mutex = mutex;
        this.wrt = full;
        this.empty = empty;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("begin:pros: "+name);
        int times = 0;

        do {
            int time = (int)(Math.random() * 100);
            try {
                sleep(time);
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            buffer[0]++;

            if (buffer[0]==1) {
                try {
                    wrt.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            mutex.release();

            try {
                mutex.acquire();
                System.out.println("Reader wants to leave : "+name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            buffer[0]--;
            System.out.println("Current readers left: "+buffer[0]+" : ");

            if (buffer[0] == 0) {
                wrt.release();
                System.out.println("All Readers left  ");
            }
            mutex.release();
        } while (times -->0);
    }
}
