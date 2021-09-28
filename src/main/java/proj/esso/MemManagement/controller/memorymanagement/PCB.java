
package proj.esso.MemManagement.controller.memorymanagement;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCB {
    public LinkedList <Proc> ready = new LinkedList<Proc>();
    public LinkedList <Proc> running = new LinkedList<Proc>();
    public LinkedList <Proc> terminated = new LinkedList<Proc>();
    
    
    /*public void addProcess(Process process) throws InterruptedException 
    {   
        if (process.getPriority() == 5){
            ready.addFirst(process);
        }  
                
        if (process.getPriority() == 4){
            int count = 0;
                if (ready.isEmpty()){
                    ready.addFirst(process);
                }
                else{
                    Process p = ready.getFirst();
                    if (process.getPriority() >= p.getPriority()){
                        ready.addFirst(process);
                    }
                    else{
                        Iterator<Process> it = ready.iterator();
                            while (it.hasNext()){
                                count++;
                                p = it.next(); 
                                if (process.getPriority() <= p.getPriority()){
                                    ready.add(count, process);
                        }
                    }
                }
            }
        }

                
        if (process.getPriority() == 3){
            int count = 0;
                if (ready.isEmpty()){
                    ready.addFirst(process);
                }
                else{
                    Process p = ready.getFirst();
                    if (process.getPriority() >= p.getPriority()){
                        ready.addFirst(process);
                    }
                    else{
                        Iterator<Process> it = ready.iterator();
                            while (it.hasNext()){
                                count++;
                                p = it.next(); 
                                if (process.getPriority() <= p.getPriority()){
                                    ready.add(count, process);
                        }
                    }
                }
            }
        }
                
        if (process.getPriority() == 2){
            int count = 0;
                if (ready.isEmpty()){
                    ready.addFirst(process);
                }
                else{
                    Process p = ready.getFirst();
                    if (process.getPriority() >= p.getPriority()){
                        ready.addFirst(process);
                    }
                    else{
                        Iterator<Process> it = ready.iterator();
                            while (it.hasNext()){
                                count++;
                                p = it.next(); 
                                if (process.getPriority() <= p.getPriority()){
                                    ready.add(count, process);
                        }
                    }
                }
            }
        }
        
        if (process.getPriority() == 1){
            ready.add(process);
        }    
    }*/
    
    public void addProcess(Proc process)
    {
        /*if (process.getPriority() == 5)
        {
            ready.addFirst(process);
        }*/
        ready.add(process);
    }
    
    public Proc getProcess (int queue)
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
    

    public void changeQueue (Proc process, int setQueue)
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
            ready.addLast(process);
            running.remove(process);
            process.setQueue(setQueue);
        }
        if (process.getQueue() == 1 && setQueue == 2)
        {
            terminated.addLast(process);
            running.remove(process);
            process.setQueue(setQueue);
        }
    }
    
    public String toString(){
                
        return terminated.toString();
    }
    
    public void printerReady(){
        for (Proc p:ready){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
            System.out.println("\n");
        }
    }
    
    public void printerRunning(){
        for (Proc p:running){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
            System.out.println("\n");
        }
    }
    
    public void printerTerminated(){
        for (Proc p:terminated){
            System.out.println("ID: "+p.getId());
            System.out.println("MemSize: "+p.getMemSize());
            System.out.println("Priority: "+p.getPriority());
            System.out.println("Queue: "+p.getQueue());
            System.out.println("TimeExec: "+p.getExecTime());
            System.out.println("\n");
        }
    }
}
