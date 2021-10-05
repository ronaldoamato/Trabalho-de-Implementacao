
package sync;

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
    private int minTime;
    private int maxTime;
    private int numReq;
    private RandomProcessGenerator randomProcessGenerator;
    private PCB pcb;
    private Scheduler scheduler;
    private Logger logger;
    private CPU cpu;
    private Heap heap;
    private Swap swap;


    public MemoryManagement(int maxSize, int minSize, int maxTime, int minTime, int numReq, int quantum, int heapSize, double heapThreshold, int numCores, double heapLowerLimit)
    {
        this.maxSize = maxSize;
        this.minSize = minSize;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.numReq = numReq;
        this.randomProcessGenerator = new RandomProcessGenerator();
        this.pcb = new PCB();
        this.logger = new Logger();
        this.heap = new Heap(heapThreshold, heapSize, heapLowerLimit);
        this.swap = new Swap();
        this.cpu = new CPU(numCores);
        this.scheduler = new Scheduler(quantum);
    }

    private void genProcesses(Logger logger)
    {
        Proc process;
        int i = 0;
        while(this.numReq > 0)
        {
            process = this.randomProcessGenerator.gen(
                    this.minSize,
                    this.maxSize,
                    this.minTime,
                    this.maxTime,
                    i
            );
            logger.addLog(String.format("CREATED PROCESS: { %s }", process.getData()));
            this.pcb.getQueue(0).add(process);
            this.numReq--;
            i++;
        }
    }

    private void consumeProcesses(Logger logger) throws NoProcessException, InterruptedException
    {
        while(this.pcb.getQueue(0).size() > 0)
        {
            // int a = 0; //not alloc
            // int b = 0; //heap
            // int c = 0;//swap
            // for(int i = 0; i < 3 ; i++)
            // {
            //         for(Proc d : this.pcb.getQueue(i))
            //     {
            //         if(d.getCurrentMem() == 0)
            //         {
            //             a++;
            //         }
            //         if(d.getCurrentMem() == 1)
            //         {
            //             b++;
            //         }
            //         if(d.getCurrentMem() == 2)
            //         {
            //             c++;
            //         }
            //     }
            // }
            // System.out.println(String.format("disc: %d | heap: %d | swap: %d", a, b, c));
            // System.out.println(String.format("total: %d", this.pcb.getQueue(0).size() + this.pcb.getQueue(1).size() + this.pcb.getQueue(2).size()));
            
            this.scheduler.run(this.cpu, this.pcb, this.logger, this.heap, this.swap);
        }
    }

    public void run() throws NoProcessException, InterruptedException
    {

        this.logger.addLog("================= STARTED =================");

        this.genProcesses(logger);

        this.consumeProcesses(this.logger);





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

        this.logger.addLog("READY: " + this.pcb.getQueue(0));
        this.logger.addLog("RUNNING: " + this.pcb.getQueue(1));
        this.logger.addLog("TERMINATED: " + this.pcb.getQueue(2));
        this.logger.addLog("================= FINISHED =================");
        //threadExecutor.execute(play);
    }

    public String getLog()
    {
        return this.logger.getLog();
    }

}
