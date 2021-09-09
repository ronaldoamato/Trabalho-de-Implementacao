
package memorymanagement;

import java.util.ArrayList;

public class PCB {
    private ArrayList <Process> ready = new ArrayList<>();
    private ArrayList <Process> running = new ArrayList<>();
    private ArrayList <Process> terminated = new ArrayList<>();
    
    public void addProcess(Process process)
    {
        if (process.getPriority() == 5)
        {
            ready.add(0, process);
        }
        ready.add(process);
    }
    
    public Process getProcess (int queue)
    {   
        if (queue == 0)
        {
            return ready.get(0);
        }
        if (queue == 1)
        {
            return running.get(0);
        }
        if (queue == 2)
        {
            return terminated.get(0);
        }
        return null;
    }
    

    public void changeQueue (Process process, int setQueue) 
    {      
        if (process.getQueue() == 0 && setQueue == 1)
        {
            running.add(process);
            ready.remove(process);
            process.setQueue(setQueue);
        }
        if (process.getQueue() == 0 && setQueue == 2)
        {
            terminated.add(process);
            ready.remove(process);
            process.setQueue(setQueue);
        }
        if (process.getQueue() == 1 && setQueue == 0)
        {
            ready.add(process);
            running.remove(process);
            process.setQueue(setQueue);
        }
        if (process.getQueue() == 1 && setQueue == 2)
        {
            terminated.add(process);
            running.remove(process);
            process.setQueue(setQueue);
        }
    }
}
