package sync;

import java.util.LinkedList;

public class Heap {
    private LinkedList<Alloc> freeSpace;
    private LinkedList<AllocProcess> occupiedSpace;
    private int usedSpace;
    private int upperLimit;
    private int lowerLimit;
    private int memSize;

    public Heap(double upperLimit, int memSize, double lowerLimit)
    {
        this.freeSpace = new LinkedList<Alloc>();
        this.freeSpace.add(new Alloc(0, memSize - 1));
        this.occupiedSpace = new LinkedList<AllocProcess>();
        this.memSize = memSize;
        this.usedSpace = 0;
        this.upperLimit = (int) (((double) memSize) * upperLimit);
        this.lowerLimit = (int) (((double) memSize) * lowerLimit);
    }

    private int getLeastUsed(Swap swap)
    {
        int leastUsed = this.occupiedSpace.get(0).getUses();
        int leastUsedIndex = 0;
        // System.out.println(this.usedSpace);
        // System.out.println( String.format("occupied space: %d | swap: %d", this.occupiedSpace.size(), swap.getSwapMem().size()));

        for(int i = 0; i < this.occupiedSpace.size() ; i++)
        {
            //System.out.println(String.format("current: %d | test: %d | ind: %d", leastUsed, this.occupiedSpace.get(i).getUses(), leastUsedIndex));
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
        int leastUsedIndex = this.getLeastUsed(swap);
        AllocProcess allocProcess = this.occupiedSpace.get(leastUsedIndex);

        while(this.usedSpace >= this.lowerLimit)
        {
            
            // System.out.println(String.format("b4 %d - %d | %d", this.usedSpace, this.occupiedSpace.getFirst().getProcess().getMemSize(), lowerLimit));

            // this.usedSpace = this.usedSpace - this.occupiedSpace.getFirst().getProcess().getMemSize();

            // this.occupiedSpace.getFirst().getProcess().setCurrentMem(2);
            // swap.addProcess(this.occupiedSpace.getFirst().getProcess());
            // this.freeSpace.add(new Alloc(this.occupiedSpace.getFirst().getStart(), this.occupiedSpace.getFirst().getOffset()));
            // this.occupiedSpace.remove(0);

            // System.out.println(String.format("after %d | %d", this.usedSpace, lowerLimit));

            leastUsedIndex = this.getLeastUsed(swap);
            allocProcess = this.occupiedSpace.get(leastUsedIndex);
            allocProcess.getProcess().setCurrentMem(2); 
            swap.addProcess(allocProcess.getProcess());
            this.freeSpace.add(new Alloc(allocProcess.getStart(), allocProcess.getOffset()));
            this.usedSpace  = this.usedSpace - allocProcess.getProcess().getMemSize();
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

    private AllocProcess findFreeSpace(int memSize, Logger logger)
    {
        int index = 0;
        int found = 0;


        for(Alloc a : this.freeSpace)
        {
            if(((a.getOffset()) > memSize - 1))
            {
                found = 1;
                break;
            }
            index++;
        }

        for(int i = 0 ; i < this.freeSpace.size() - 1; i++)
        {
            //System.out.println(String.format("%d < %d ", this.freeSpace.get(index).getOffset(), memSize - 1));
            if(((this.freeSpace.get(index).getOffset()) > memSize - 1))
            {
                index = i;
                found = 1;
            }
        }

        if(found == 0)
        {
            logger.addLog("DEFRAGGING HEAP");
            this.defrag();

            for(Alloc a : this.freeSpace)
            {
                if(((a.getOffset()) > memSize - 1))
                {
                    found = 1;
                    break;
                }
                index++;
            }

            // for(int i = 0 ; i <= this.freeSpace.size() - 1; i++)
            // {
            //     //System.out.println(String.format("%d < %d ", this.freeSpace.get(index).getOffset(), memSize - 1));
            //     if(((this.freeSpace.get(index).getOffset()) > memSize - 1))
            //     {
            //         index = i;
            //         found = 1;
            //     }
            // }

            // for(int i = 0 ; i < this.freeSpace.size(); i++)
            // {
            //     if(this.freeSpace.get(index).getOffset() < memSize - 1)
            //     {
            //         index = i;
            //     }
            // }

        }

        int start = this.freeSpace.get(index).getStart();

        AllocProcess allocProcess = new AllocProcess(start,memSize - 1);
        this.freeSpace.get(index).setStart(start + memSize);

        return allocProcess;
    }

    void allocProcess(Proc process, Swap swap, Logger logger)
    {

        if(this.usedSpace > this.upperLimit)
        {
            logger.addLog(String.format("HEAP AT %d/%d CAPACITY", this.usedSpace, this.memSize));
            removeAllocProcesses(swap); //clears memory until upperLimit
        }

        int currentMem = process.getCurrentMem();

        switch (currentMem)
        {
            case 0 : {
                AllocProcess allocProcess = this.findFreeSpace(process.getMemSize(), logger);
                allocProcess.setProcess(process);
                process.setCurrentMem(1);
                allocProcess.setUses(allocProcess.getUses() + 1);
                this.occupiedSpace.add(allocProcess);
                this.usedSpace = this.usedSpace + process.getMemSize();
                logger.addLog(String.format("PROCESS({ %s }) ALLOCATED ON HEAP AT POS %d WITH OFFSET %d (%d : %d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart(), (allocProcess.getStart() + allocProcess.getOffset())));
                break;
            }
            case 1 : {
                AllocProcess allocProcess = this.occupiedSpace.get(this.findProcessById(process.getId()));
                allocProcess.setUses(allocProcess.getUses() + 1);
                logger.addLog(String.format("PROCESS({ %s }) ALREADY ALLOCATED ON HEAP AT POS %d WITH OFFSET %d (%d : %d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart(), (allocProcess.getStart() + allocProcess.getOffset())));
                break;
            }
            case 2 : {
                int swapProcessIndex = swap.findById(process.getId());
                Proc swapProcess = swap.getSwapMem().get(swapProcessIndex);
                AllocProcess allocProcess = this.findFreeSpace(swapProcess.getMemSize(), logger);
                allocProcess.setProcess(swapProcess);
                this.occupiedSpace.add(allocProcess);
                this.usedSpace = this.usedSpace + swapProcess.getMemSize();
                swapProcess.setCurrentMem(1);
                allocProcess.setUses(allocProcess.getUses() + 1);
                swap.getSwapMem().remove(swapProcessIndex);
                logger.addLog(String.format("PROCESS({ %s }) BROUGHT FROM SWAP AND ALLOCATED INTO HEAP AT POS %d WITH OFFSET %d (%d : %d)", process.getData(), allocProcess.getStart(), allocProcess.getOffset(), allocProcess.getStart(), (allocProcess.getStart() + allocProcess.getOffset())));
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
