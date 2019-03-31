package Cigarette_Smokers;

public class Smoker extends Thread {

    private Table smokingtable;
    private String element;
    private int elementNumber;
    MySemaphores mutex;

    public Smoker(Table pSmokingtable, int pElementNumber, String pName, MySemaphores mutex)
    {
        elementNumber = pElementNumber;
        this.smokingtable = pSmokingtable;
        setName(pName);
        this.mutex = mutex;
    }

    @Override
    public void run()
    {
        while(true)
        {
            element = smokingtable.getSmokerElement(elementNumber);

            if (!smokingtable.hasElement(element) && !smokingtable.isEmpty())
            {
                System.out.println(getName() + " has " + element);
                try {
                    System.out.println(getName() + " smokes.");
                    System.out.println(getName() + " tells the SmokingAgent to start the next round.");
                    smokingtable.getAgentElements();
                    mutex.release();
                } catch (Exception e) {}
            }
        }

    }
}
