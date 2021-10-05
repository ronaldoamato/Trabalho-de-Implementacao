
package sync;

import java.util.LinkedList;

class PCB {
    private LinkedList <Proc> ready;
    private LinkedList <Proc> running;
    private LinkedList <Proc> terminated;

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

    private String getQueueName(int queue)
    {
        String name = "";
        switch(queue)
        {
            case 0 : name = "READY"; break;
            case 1 : name = "RUNNING"; break;
            case 2 : name = "TERMINATED"; break;
            default : break;
        }

        return name;
    }
    
    private Proc changeQueueAux (int queue, int pos) throws NoProcessException
    {
        try
        {
            Proc process = null;

            switch (queue)
            {
                case 0 :
                {
                    process = this.ready.get(pos);
                    this.ready.remove(pos);
                    break;
                }
                case 1 :
                {
                    process = this.running.get(pos);
                    this.running.remove(pos);
                    break;
                }
                case 2 :
                {
                    process = this.terminated.get(pos);
                    this.terminated.remove(pos);
                    break;
                }
                default: break;
            }
            if(process == null)
            {
                throw new NoProcessException();
            }
            return process;
        }
        catch(NoProcessException noProcessException)
        {
            return null;
        }

    }

    Proc changeQueue(int currentPos, int currentQueue, int newQueue, Logger logger) throws NoProcessException
    {
        
        Proc process = this.changeQueueAux(currentQueue, currentPos);
        String currentQueueName = this.getQueueName(currentQueue);
        switch (newQueue)
        {
            case 0:
            {
                this.ready.addLast(process);
                process.setQueue(0);
                logger.addLog(String.format("CHANGED PROCESS({ %s }) FROM %s TO READY", process.getData(), currentQueueName));
                break;
            }
            case 1:
            {
                this.running.addLast(process);
                process.setQueue(1);
                logger.addLog(String.format("CHANGED PROCESS({ %s }) FROM %s TO RUNNING", process.getData(), currentQueueName));
                break;
            }
            case 2:
            {
                this.terminated.add(process);
                process.setQueue(2);
                logger.addLog(String.format("CHANGED PROCESS({ %s }) FROM %s TO TERMINATED", process.getData(), currentQueueName));
                break;
            }
            default : break;
        }

        return process;
        
    }

    LinkedList<Proc> getQueue(int queue)
    {
        if(queue == 0) return this.ready;
        if(queue == 1) return this.running;
        if(queue == 2) return this.terminated;
        return null;
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
