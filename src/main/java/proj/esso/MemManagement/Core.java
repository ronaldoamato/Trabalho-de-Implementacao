
package memorymanagement;

import static java.lang.Thread.sleep;


public class Core {
   
    public void exec(Process process) throws InterruptedException{
        int time = process.getExecTime();
        time = time - 10;
        sleep(10);
        process.setExecTime(time);
    }
    
}
