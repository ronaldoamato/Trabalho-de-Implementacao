
package memorymanagement;

import java.util.ArrayList;
import java.util.LinkedList;

public class PCB {
    public LinkedList <Process> ready = new LinkedList<Process>();
    public LinkedList <Process> running = new LinkedList<Process>();
    public LinkedList <Process> terminated = new LinkedList<Process>();
    
    public void addProcess(Process process)
    {
        if (process.getPriority() == 5)
        {
            /*for (int i = 0; i<ready.size(); i++){
                ready.set(i+1, ready.get(i));
            }*/
            ready.addFirst(process);
        }
        ready.add(process);
    }
    
    public Process getProcess (int queue)
    {   
        if (queue == 0)
        {
            if (ready.isEmpty()){
                return null;
            }
            return ready.getFirst();
        }
        if (queue == 1)
        {
            if (running.isEmpty()){
                return null;
            }
            return running.getFirst();
        }
        if (queue == 2)
        {
            if (terminated.isEmpty()){
                return null;
            }
            return terminated.getFirst();
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
    
    public String toString(){
                
        return terminated.toString();
    }
    
    public void printerReady(){
        for (Process p:ready){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
        }
    }
    
    public void printerRunning(){
        for (Process p:running){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
        }
    }
    
    public void printerTerminated(){
        for (Process p:terminated){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
        }
    }
}
