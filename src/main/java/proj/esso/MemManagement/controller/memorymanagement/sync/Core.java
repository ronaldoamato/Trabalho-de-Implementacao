
package proj.esso.MemManagement.controller.memorymanagement.sync;

import static java.lang.Thread.sleep;


class Core {

    private boolean running;

    public Core()
    {
        this.running = false;
    }

   
    public Proc exec(Proc process, int quantum) throws InterruptedException{

        int time = ((process.getExecTime() - quantum));
        time = (time <= 0) ? 0 : time;
        sleep(quantum);
        process.setExecTime(time);
        return process;
    }

    boolean getRunning(){ return this.running; }
    
}
