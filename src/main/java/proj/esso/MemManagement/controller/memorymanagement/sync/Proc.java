
package proj.esso.MemManagement.controller.memorymanagement.sync;

public class Proc {

    private String id;
    private int memSize, execTime, currentQueue;
    private int currentMem;
    
    public Proc(int mem, int time)
    {
        this.memSize = mem;
        this.execTime = time;
        this.currentQueue = 0;
        this.currentMem = 0; //0: not allocated; 1: heap; 2: swap
    }

    void setQueue(int currentQueue) {
        this.currentQueue = currentQueue;
    }

    int getQueue() {
        return currentQueue;
    }

    int getMemSize() {
        return memSize;
    }

    int getExecTime() {
        return execTime;
    }

    String getId() {
        return id;
    }

    void setId(String id){ this.id = id; }
    
    void setExecTime(int execTime) {
        this.execTime = execTime;
    }

    int getCurrentMem(){ return this.currentMem; }

    void setCurrentMem(int newMem){ this.currentMem = newMem; }
    
    public String getData()
    {
        return String.format("id: %s, memSize: %d, execTime: %d, currentQueue: %d",
                this.id,
                this.memSize,
                this.execTime,
                this.currentQueue);
    }
}
