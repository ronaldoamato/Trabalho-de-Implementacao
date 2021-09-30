package proj.esso.MemManagement.controller.memorymanagement.sync;

import java.util.LinkedList;

public class Swap {

    private LinkedList<Proc> swapMem;

    public Swap()
    {
        this.swapMem = new LinkedList<Proc>();
    }

    int findById(String id)
    {

        int index = -1;
        for(int i = 0 ; i < swapMem.size() ; i++)
        {
            if(swapMem.get(i).getId().equals(id))
            {
                index = i;
            }
        }

        return index;
    }

    void addProcess(Proc process)
    {
        this.swapMem.add(process);
    }

    LinkedList<Proc> getSwapMem()
    {
        return this.swapMem;
    }


}
