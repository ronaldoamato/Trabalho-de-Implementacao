
package memorymanagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MemoryManagement {
    
    /*int minSize, maxSize, reqNumber;
    
    public MemoryManagement(int minSize, int maxSize, int reqNumber){
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.reqNumber = reqNumber;
    }
   */
    
    
    public static void main(String[] args) {
        
        int minSize = 1, maxSize = 10; //reqNumber = 5;
        RandomProcessGenerator task = new RandomProcessGenerator(minSize, maxSize);
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        
        //Scheduler play = new Scheduler();
        
        System.out.println("Starting\n");
        
        //while (reqNumber>0){
            //task.run();
            threadExecutor.execute(task);
            //reqNumber--;
        //}
        threadExecutor.shutdown();
        //threadExecutor.execute(play);
    }
}
