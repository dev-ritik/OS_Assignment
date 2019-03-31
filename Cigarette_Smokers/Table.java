package Cigarette_Smokers;

import java.util.ArrayList;
import java.util.Random;

public class Table {

    private ArrayList<String> allElements = new ArrayList<>();
    private ArrayList<String> agentElements = new ArrayList<>();

    public Table()
    {
        allElements.add("tabacco");
        allElements.add("paper");
        allElements.add("matches");
    }

    public void setAgentElements()
    {
        Random random = new Random();

        agentElements.clear();

        ArrayList<String> copyAllElements = new ArrayList<>( allElements);

        int element1 = random.nextInt(copyAllElements.size());
        agentElements.add(copyAllElements.get(element1));

        copyAllElements.remove(element1);
        int element2 = random.nextInt(copyAllElements.size());
        agentElements.add(copyAllElements.get(element2));
    }

    public boolean isEmpty()
    {
        return (agentElements.size() == 0);
    }

    public synchronized String getAgentElements()
    {
        notifyAll();
        return agentElements.toString();
    }

    public synchronized String getSmokerElement(int pElement)
    {
        try {
            this.wait();
        } catch (Exception e) {}
        return allElements.get(pElement);
    }

    public boolean hasElement(String elementName)
    {
        return (agentElements.contains(elementName));
    }
}
