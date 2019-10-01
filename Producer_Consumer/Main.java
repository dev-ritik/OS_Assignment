package Producer_Consumer;
public class Main {

    public static void main(String args[]) throws InterruptedException {
        int size = 5;
        MySemaphores mutex = new MySemaphores(1);
        MySemaphores full = new MySemaphores(0);
        MySemaphores empty = new MySemaphores(1);

        int[] buffer = new int[size];

        Producer producer = new Producer("prod", mutex, full, empty, buffer);
        Consumer constomer = new Consumer("cons", mutex, full, empty, buffer);

        producer.start();
        constomer.start();

        producer.join();
        constomer.join();

        System.out.println("done");

    }
}
