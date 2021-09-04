
package memorymanagement;

import java.util.ArrayList;

public class PCB {
    private ArrayList <Process> novo = new ArrayList<>();
    private ArrayList <Process> ready = new ArrayList<>();
    private ArrayList <Process> waiting = new ArrayList<>();
    private ArrayList <Process> running = new ArrayList<>();
    private ArrayList <Process> terminated = new ArrayList<>();
    
    public void addProcess(Process process)
    {
        novo.add(process);
    }
    
    public Process getProcess (Process process)
    {   
        if (process.getQueue() == 0)
        {
            return novo.get(0);
        }
        if (process.getQueue() == 1)
        {
            return ready.get(0);
        }
        if (process.getQueue() == 2)
        {
            return waiting.get(0);
        }
        if (process.getQueue() == 3)
        {
            return running.get(0);
        }
        if (process.getQueue() == 4)
        {
            return terminated.get(0);
        }
        return null;
    }

    public void changeQueue (Process process, int setQueue) 
    {
        process.setQueue(setQueue);
        if (process.getQueue() == 1)
        {
            ready.add(process);
        }
        if (process.getQueue() == 2)
        {
            waiting.add(process);
        }
        if (process.getQueue() == 3)
        {
            running.add(process);
        }
        if (process.getQueue() == 4)
        {
            terminated.add(process);
        }
    }
}
