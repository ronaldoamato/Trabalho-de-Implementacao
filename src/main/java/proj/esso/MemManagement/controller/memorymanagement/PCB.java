
package proj.esso.MemManagement.controller.memorymanagement;

import java.util.LinkedList;

class NoProcessException extends Exception
{
    public NoProcessException()
    {
        System.out.println("No process available");
    }

}



class PCB {
    public LinkedList <Proc> ready;
    public LinkedList <Proc> running;
    public LinkedList <Proc> terminated;

    public PCB()
    {
        this.ready = new LinkedList<Proc>();
        this.running = new LinkedList<Proc>();
        this.terminated = new LinkedList<Proc>();
    }
    
//    public void addNewProcess(Proc process)
//    {
//        this.ready.add(process);
//    }
    
    Proc getProcess (int queue, Logger logger) throws NoProcessException
    {
        try
        {
            Proc process = null;

            switch (queue)
            {
                case 0 :
                {
                    process = this.ready.getFirst();
                    this.ready.remove();
                    logger.addLog(String.format("GOT PROCESS({ %s }) FROM READY QUEUE", process.getData()));
                    break;
                }
                case 1 :
                {
                    process = this.running.getFirst();
                    this.running.remove();
                    logger.addLog(String.format("GOT PROCESS({ %s }) FROM RUNNING QUEUE", process.getData()));
                    break;
                }
                case 2 :
                {
                    process = this.terminated.getFirst();
                    this.terminated.remove();
                    logger.addLog(String.format("GOT PROCESS({ %s }) FROM TERMINATED QUEUE", process.getData()));
                    break;
                }
                default: break;
            }
            if(process == null)
            {
                logger.addLog(String.format("ERROR: NO PROCESS FOUND ON QUEUE "));
                throw new NoProcessException();
            }
            return process;
        }
        catch(NoProcessException noProcessException)
        {
            return null;
        }

//        if (queue == 0)
//        {
//            if (ready.isEmpty()){
//                return null;
//            }
//            return ready.getFirst();
//        }
//        if (queue == 1)
//        {
//            if (running.isEmpty()){
//                return null;
//            }
//            return running.getFirst();
//        }
//        if (queue == 2)
//        {
//            if (terminated.isEmpty()){
//                return null;
//            }
//            return terminated.getFirst();
//        }
//        return null;
    }

    void setProcess(Proc process, int queue, Logger logger)
    {
        switch (queue)
        {
            case 0:
            {
                this.ready.add(process);
                logger.addLog(String.format("SET PROCESS({ %s }) INTO READY QUEUE", process.getData()));
                process.setQueue(0);
                break;
            }
            case 1:
            {
                this.running.add(process);
                logger.addLog(String.format("SET PROCESS({ %s }) INTO RUNNING QUEUE", process.getData()));
                process.setQueue(1);
                break;
            }
            case 2:
            {
                this.terminated.add(process);
                logger.addLog(String.format("SET PROCESS({ %s }) INTO TERMINATED QUEUE", process.getData(), queue));
                process.setQueue(2);
                break;
            }
            default : break;
        }
    }

    

//    void changeQueue (Proc process, int setQueue)
//    {
//        if (process.getQueue() == 0 && setQueue == 1)
//        {
//            running.add(process);
//            ready.remove(process);
//            process.setQueue(setQueue);
//        }
//        if (process.getQueue() == 0 && setQueue == 2)
//        {
//            terminated.add(process);
//            ready.remove(process);
//            process.setQueue(setQueue);
//        }
//        if (process.getQueue() == 1 && setQueue == 0)
//        {
//            ready.addLast(process);
//            running.remove(process);
//            process.setQueue(setQueue);
//        }
//        if (process.getQueue() == 1 && setQueue == 2)
//        {
//            terminated.addLast(process);
//            running.remove(process);
//            process.setQueue(setQueue);
//        }
//    }

}
