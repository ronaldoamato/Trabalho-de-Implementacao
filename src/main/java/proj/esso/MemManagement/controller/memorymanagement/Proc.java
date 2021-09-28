
package proj.esso.MemManagement.controller.memorymanagement;

public class Proc {

    private int memSize, execTime, queue, mem, priority;
    private String id;
    
    
    public Proc(){
        
    }
    
    public Proc(int mem, int time, String id, int priority)
    {
        this.memSize = mem;
        this.execTime = time;
        this.id = id;
        this.queue = 0;
        this.priority = priority;
        
    }

    public int getMem() {
        return mem;
    }

    public int getPriority() {
        return priority;
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
    
    public void setExecTime(int execTime) {
        this.execTime = execTime;
    }
    
    public String toString()
    {
        return this.id+"\n"+execTime+"\n"+memSize+"\n"+priority+"\n";
        
    }
}
