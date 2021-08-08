
package memorymanager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainClass {
    public static void main(String[] args) {
//        int corePoolsize = 2;
//        int maximumPoolsize = 500;
//        int keepAliveTime = 10;
//        int queueSize = 10;
//        ThreadFactory executorService = Executors.defaultThreadFactory();
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolsize, maximumPoolsize, 
//                keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize), executorService, new RejectionHandler()) ;
//        

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++)
        {
            executorService.execute(new Pool(i));
        }
        executorService.shutdown();
    }
}



 //new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());