
package memorymanagement;

public class Process extends PCB{
    private int memSize;
    private int execTime;
    private String id;
    private int queue;
    
    public Process(int mem, int time, String id)        
    {
        this.memSize = mem;
        this.execTime = time;
        this.id = id;
        this.queue = 0;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getQueue() {
        return queue;
    }

    public int getMemSize() {
        return memSize;
    }

    public int getExecTime() {
        return execTime;
    }

    public String getId() {
        return id;
    }
    
    public String toString()
    {
        return this.id+"";
    }
}
