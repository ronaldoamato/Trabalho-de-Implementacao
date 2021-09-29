package proj.esso.MemManagement.controller.memorymanagement;

import java.util.ArrayList;

class CPU {

    private int quantum;
    private ArrayList<Core> cores;

    public CPU(int quantum, int numCores)
    {
        this.quantum = quantum;
        this.cores = new ArrayList<Core>();

        for(int i = 0; i < numCores; i++)
        {
            this.cores.add(new Core());
        }
    }

    Proc run(Proc process, Logger logger) throws InterruptedException
    {
        this.cores.get(0).exec(process, this.quantum);
        return process;
    }


}
