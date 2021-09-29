
package proj.esso.MemManagement.controller.memorymanagement.sync;

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
    private int heapSize;
    private  int quantum;
    private RandomProcessGenerator randomProcessGenerator;
    private PCB pcb;
    private Scheduler scheduler;
    private Logger logger;
    private CPU cpu;


    public MemoryManagement(int maxSize, int minSize, int maxTime, int minTime, int numReq, int quantum, int heapSize)
    {
        this.maxSize = maxSize;
        this.minSize = minSize;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.numReq = numReq;
        this.quantum = quantum;
        this.heapSize = heapSize;
        this.randomProcessGenerator = new RandomProcessGenerator();
        this.pcb = new PCB();
        this.logger = new Logger();
        this.cpu = new CPU(1);
    }

    private void genProcesses(Logger logger)
    {
        Proc process;
        while(this.numReq > 0)
        {
            process = this.randomProcessGenerator.gen(
                    this.minSize,
                    this.maxSize,
                    this.minTime,
                    this.maxTime
            );
            logger.addLog(String.format("CREATED PROCESS: { %s }", process.getData()));
            this.pcb.setProcess(process, 0,  logger);
            this.numReq--;
        }
    }

    private void consumeProcesses(Logger logger)
    {
        while(this.pcb.ready.size() > 0 )
        {

            this.cpu.run(
                this.pcb.getProcess(0, logger);
            );

        }
    }

    public void run() {

        this.logger.addLog("================= STARTED =================");

        this.genProcesses(logger);





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

        this.logger.addLog("================= FINISHED =================");
        //threadExecutor.execute(play);
    }

    public String getLog()
    {
        return this.logger.getLog();
    }

}