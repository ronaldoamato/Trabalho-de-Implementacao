package proj.esso.MemManagement.controller.memorymanagement.sync;

import java.util.LinkedList;

public class Heap {
    private LinkedList<Alloc> freeSpace;
    private LinkedList<AllocProcess> occupiedSpace;
    private int usedSpace;
    private int threshold;
    private int memSize;

    public Heap(double threshold, int memSize)
    {
        this.freeSpace = new LinkedList<Alloc>();
        this.freeSpace.add(new Alloc(0, memSize - 1));
        this.occupiedSpace = new LinkedList<AllocProcess>();
        this.memSize = memSize;
        this.usedSpace = 0;
        this.threshold = (int) (((double) memSize) * threshold);
    }

    private boolean checkThreshold() //true: needs to clear mem;
    {
        return usedSpace < threshold;
    }

    private int getLeastUsed()
    {
        int leastUsed = this.occupiedSpace.get(0).getUses();
        int leastUsedIndex = 0;

        for(int i = 0; i < this.occupiedSpace.size(); i++)
        {
            if(leastUsed > this.occupiedSpace.get(i).getUses())
            {
                leastUsed = this.occupiedSpace.get(i).getUses();
                leastUsedIndex = i;
            }
        }

        return leastUsedIndex;
    }

    private void removeAllocProcesses(Swap swap)
    {
        int leastUsedIndex = 0;
        AllocProcess allocProcess;

        while(usedSpace > threshold)
        {
            leastUsedIndex = this.getLeastUsed();
            allocProcess = this.occupiedSpace.get(leastUsedIndex);
            allocProcess.getProcess().setCurrentMem(2);
            swap.addProcess(allocProcess.getProcess());
            this.freeSpace.add(new Alloc(allocProcess.getStart(), allocProcess.getOffset()));
            this.usedSpace -= allocProcess.getOffset();
            this.occupiedSpace.remove(leastUsedIndex);
        }
    }

    private void defrag()
    {
        int startOffset = 0;
        this.freeSpace.clear();

        for(AllocProcess allocProcess : occupiedSpace)
        {
            allocProcess.setStart(startOffset);
            startOffset += allocProcess.getOffset();
            allocProcess.setOffset(startOffset - 1);
        }

        this.freeSpace.add(new Alloc(startOffset, this.memSize - 1));
    }

    private int findProcessById(String id)
    {
        int index = 0;
        for(int i = 0; i < this.occupiedSpace.size() ; i ++)
        {
            if(this.occupiedSpace.get(i).getProcess().getId().equals(id))
            {
                index = i;
            }
        }

        return index;
    }


    private AllocProcess findFreeSpace(int memSize)
    {
        int index = -1;

        for(int i = 0 ; i < this.freeSpace.size(); i++)
        {
            if(this.freeSpace.get(index).getOffset() < memSize - 1)
            {
                index = i;
            }
        }

        if(index == -1)
        {
            this.defrag();
            this.findFreeSpace(memSize);
        }

        int start = this.freeSpace.get(index).getStart();

        AllocProcess allocProcess = new AllocProcess(start,memSize - 1);
        this.freeSpace.get(index).setStart(start + memSize);

        return allocProcess;
    }

    void allocProcess(Proc process, Swap swap, Logger logger)
    {
        if(this.checkThreshold())
        {
            logger.addLog(String.format("HEAP AT %d CAPACITY", this.usedSpace/this.memSize));
            removeAllocProcesses(swap); //clears memory until threshold
        }

        switch (process.getCurrentMem())
        {
            case 0 : {
                AllocProcess allocProcess = this.findFreeSpace(process.getMemSize());
                allocProcess.setProcess(process);
                process.setCurrentMem(1);
                allocProcess.setUses(allocProcess.getUses() + 1);
                this.occupiedSpace.add(allocProcess);
                this.usedSpace += process.getMemSize();
                logger.addLog(String.format("PROCESS({ %s }) ALLOCATED ON HEAP AT POS %d WITH OFFSET %d (%d:%d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart() + allocProcess.getOffset()));
                break;
            }
            case 1 : {
                AllocProcess allocProcess = this.occupiedSpace.get(this.findProcessById(process.getId()));
                allocProcess.setUses(allocProcess.getUses() + 1);
                logger.addLog(String.format("PROCESS({ %s }) ALREADY ALLOCATED ON HEAP AT POS %d WITH OFFSET %d (%d:%d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart() + allocProcess.getOffset()));
                break;
            }
            case 2: {
                int swapProcessIndex = swap.findById(process.getId());
                Proc swapProcess = swap.getSwapMem().get(swapProcessIndex);
                AllocProcess allocProcess = this.findFreeSpace(swapProcess.getMemSize());
                allocProcess.setProcess(swapProcess);
                this.usedSpace += swapProcess.getMemSize();
                swapProcess.setCurrentMem(1);
                allocProcess.setUses(allocProcess.getUses() + 1);
                swap.getSwapMem().remove(swapProcessIndex);
                logger.addLog(String.format("PROCESS({ %s }) BROUGHT FROM SWAP AND ALLOCATED INTO HEAP AT POS %d WITH OFFSET %d (%d:%d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart() + allocProcess.getOffset()));
                break;
            }
            default: break;
        }
    }








//    private int memSize = 50;
//    private boolean[] memTable = new boolean [memSize];
//    private Proc[] memory = new Proc[memSize];
//    private int size;
//
//    private final Lock lock = new ReentrantLock();
//    private final Condition reading = lock.newCondition();
//    private final Condition writing = lock.newCondition();
//
//    private boolean read = false, write = false;
//
//
//    public Heap(int memSize){
//
//    }
//
//    public Heap(int size){
//        this.size = size;
//    }
//
//    public boolean[] getMemory() {
//        return memTable;
//    }
//
//
//    public void alloc(Proc process){ //Algoritmo first fit
//        int count = 0, i, j;
//
//        for (i=0; i<memSize; i++) {
//            if (memTable[i]==false){
//
//                for (j = 0; j<process.getMemSize(); j++){
//                    if ((memTable[j]==false)){
//                        count++;
//                    }
//                    if (count == process.getMemSize()){
//                        break;
//                    }
//                }
//                for (int k = i; count >= 0; k++){
//                    setStatus(k, true);
//                    memory[k]=process;
//                    count--;
//                }
//               break;
//            }
//        }
//        //System.out.println("Memoria Alocada:");
//        //printMemory();
//
//    }
//
//    public void desalloc(Proc process){
//        for (int i = 0; i<memSize; i++){
//            if (memory[i] == process){
//                memTable[i] = false;
//            }
//        }
//    }
//
//    public void printMemory(){
//        for (int i = 0; i<memSize; i++){
//            if (memTable[i]!=false){
//                    System.out.println("Status: "+memTable[i]);
//                    System.out.println("Position: "+i);
//                    System.out.println("ID: "+memory[i].getId());
//            }
//        }
//        System.out.println("\n");
//    }
//
//    public void setStatus(int position, boolean status){
//        memTable[position] = status;
//    }
//
    
    
}
