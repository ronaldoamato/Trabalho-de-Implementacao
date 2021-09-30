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

    private void removeAllocProcesses()
    {

        while(usedSpace > threshold)
        {

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







    private int memSize = 50;
    private boolean[] memTable = new boolean [memSize];
    private Proc[] memory = new Proc[memSize];
    private int size;
    
    private final Lock lock = new ReentrantLock();
    private final Condition reading = lock.newCondition();
    private final Condition writing = lock.newCondition();
    
    private boolean read = false, write = false;
    
    
    public Heap(int memSize){
        
    }
    
    public Heap(int size){
        this.size = size;
    }

    public boolean[] getMemory() {
        return memTable;
    }

    
    public void alloc(Proc process){ //Algoritmo first fit
        int count = 0, i, j;
        
        for (i=0; i<memSize; i++) {
            if (memTable[i]==false){
                
                for (j = 0; j<process.getMemSize(); j++){
                    if ((memTable[j]==false)){
                        count++;    
                    }
                    if (count == process.getMemSize()){
                        break;
                    }
                }
                for (int k = i; count >= 0; k++){
                    setStatus(k, true);
                    memory[k]=process;
                    count--;
                }
               break;  
            }
        }
        //System.out.println("Memoria Alocada:");
        //printMemory();
        
    }
    
    public void desalloc(Proc process){
        for (int i = 0; i<memSize; i++){
            if (memory[i] == process){
                memTable[i] = false;
            }
        }
    }
    
    public void printMemory(){
        for (int i = 0; i<memSize; i++){
            if (memTable[i]!=false){
                    System.out.println("Status: "+memTable[i]);
                    System.out.println("Position: "+i);
                    System.out.println("ID: "+memory[i].getId());
            }
        }
        System.out.println("\n");
    }
    
    public void setStatus(int position, boolean status){
        memTable[position] = status;
    }
    
    
    
}
