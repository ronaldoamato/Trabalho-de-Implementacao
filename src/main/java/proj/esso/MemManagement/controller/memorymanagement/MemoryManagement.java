
package proj.esso.MemManagement.controller.memorymanagement;

public class MemoryManagement {
    
    /*int minSize, maxSize, reqNumber;
    
    public MemoryManagement(int minSize, int maxSize, int reqNumber){
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.reqNumber = reqNumber;
    }
   */
    private int maxSize;
    private int minSize;
    private int numReq;
    private RandomProcessGenerator processGen;
    private PCB pcb;
    private Scheduler scheduler;
    private Logger logger;


    public MemoryManagement(int maxSize, int minSize, int numReq)
    {
        this.maxSize = maxSize;
        this.minSize = minSize;
        this.numReq = numReq;
        this.logger = new Logger();
    }

    public void run() {

        this.logger.addLog("================= Started =================");

        this.processGen = new RandomProcessGenerator(minSize, maxSize, numReq);

//        ExecutorService threadExecutor = Executors.newCachedThreadPool();
//
//        //Scheduler play = new Scheduler();
//
//        //System.out.println("Starting\n");
//
//        //while (reqNumber>0){
//            //task.run();
//            threadExecutor.execute(task);
//            //reqNumber--s
//        //}
//        threadExecutor.shutdown();

        this.logger.addLog("================= Finished =================");
        //threadExecutor.execute(play);
    }

    public String getLog()
    {
        return this.logger.getLog();
    }

}
