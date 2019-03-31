package Sleeping_Barber;

class MySemaphores {
    private int value;

    public MySemaphores(int value) {
        this.value=value;
    }

    public synchronized void acquire() throws InterruptedException {
       
        if(value > 0){
            value--;
        }
       
        else{
            this.wait();
            value--;
        }
    }

    public synchronized void release() {
        value++;

        if(value > 0){
            this.notify();
        }
    }
}