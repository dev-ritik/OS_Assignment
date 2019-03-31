package Cigarette_Smokers;

public class Main {

    public static void main(String[] args) {
        Table smokingtable = new Table();
        MySemaphores mutex = new MySemaphores(1);

        SmokingAgent controlAgent = new SmokingAgent(smokingtable, mutex);


        for (int i = 0; i < 3; i++)
        {
            // each Smoker-thread gets the controlAgent, so the thread can wake up the SmokingAgent and we don't have to do notifyAll();
            Smoker smokerThread = new Smoker(smokingtable, i, "Smoker " + Integer.toString(i+1), mutex);
            smokerThread.start();
        }
        controlAgent.start();

    }
}
