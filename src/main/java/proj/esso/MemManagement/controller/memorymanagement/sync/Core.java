
package proj.esso.MemManagement.controller.memorymanagement.sync;

import static java.lang.Thread.sleep;


class Core {

    private boolean running;

    Core()
    {
        this.running = false;
    }

   
    void exec(Proc process, int quantum) throws InterruptedException{
        this.running = true;
        int time = ((process.getExecTime() - quantum));
        time = (time <= 0) ? 0 : time;
        sleep(quantum);
        process.setExecTime(time);
        this.running = false;
    }

    boolean getRunning(){ return this.running; }
    
}
