
package sync;

import static java.lang.Thread.sleep;


class Core {

    private boolean running;

    public Core()
    {
        this.running = false;
    }
   
    void exec(Proc process, int quantum, Logger logger, Heap heap, Swap swap, int coreNum) throws InterruptedException{
        this.running = true;
        heap.allocProcess(process, swap, logger);
        sleep(quantum);
        int time = ((process.getExecTime() - quantum));
        time = (time <= 0) ? 0 : time;
        process.setExecTime(time);
        logger.addLog(String.format("PROCESS({ %s }) EXECUTED FOR %dms AT CORE %d", process.getData(), quantum, coreNum));
        this.running = false;
    }

    boolean getRunning(){ return this.running; }
    
}
