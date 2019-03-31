package Cigarette_Smokers;

public class SmokingAgent extends Thread {

    private Table smokingtable;
    private MySemaphores mutex;

    public SmokingAgent(Table pSmokingtable, MySemaphores mutex)
    {
        smokingtable = pSmokingtable;
        this.mutex = mutex;
    }

    @Override
    public void run()
    {
        while(true)
        {
            smokingtable.setAgentElements();
            System.out.println("The agents puts " + smokingtable.getAgentElements() + " on the Table.");
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
