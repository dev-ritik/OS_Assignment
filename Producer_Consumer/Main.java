package Producer_Consumer;
public class Main {

    public static void main(String args[]) throws InterruptedException {
        int size = 5;
        MySemaphores mutex = new MySemaphores(1);
        MySemaphores full = new MySemaphores(0);
        MySemaphores empty = new MySemaphores(1);

        int[] buffer = new int[size];

        Producer prod = new Producer("prod", mutex, full, empty, buffer);
        Consumer cons = new Consumer("cons", mutex, full, empty, buffer);

        prod.start();
        cons.start();

        prod.join();
        cons.join();

        System.out.println("done");

    }
}
