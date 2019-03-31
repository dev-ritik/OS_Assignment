package Readers_Writers;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        int size = 1;
        MySemaphores mutex = new MySemaphores(1);
        MySemaphores full = new MySemaphores(1);
        MySemaphores empty = new MySemaphores(1);

        int[] buffer = new int[size];

        ArrayList<Reader> readers = new ArrayList<>();
        ArrayList<Writer> writers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            readers.add(new Reader("Readers_Writers  " +i, mutex, full, empty, buffer));
            writers.add(new Writer("write "+i, mutex, full, empty, buffer));
        }

        for (Reader r:readers) {
            r.start();
        }

        for (Writer w:writers ) {
            w.start();
        }

        for (Writer w:writers ) {
            w.join();
        }

        for (Reader r:readers) {
            r.join();
        }

        System.out.println("done");

    }
}
