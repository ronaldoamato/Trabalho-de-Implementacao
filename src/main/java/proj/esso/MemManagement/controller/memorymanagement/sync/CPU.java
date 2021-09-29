package proj.esso.MemManagement.controller.memorymanagement.sync;

import java.util.ArrayList;

class CPU {

    private ArrayList<Core> cores;

    public CPU(int numCores)
    {
        this.cores = new ArrayList<Core>();

        for(int i = 0; i < numCores; i++)
        {
            this.cores.add(new Core());
        }
    }

    Proc run(Proc process, int quantum, Logger logger) throws InterruptedException
    {
        this.cores.get(0).exec(process, quantum);
        return process;
    }


}
