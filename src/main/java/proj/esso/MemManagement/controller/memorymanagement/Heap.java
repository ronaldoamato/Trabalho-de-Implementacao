
package proj.esso.MemManagement.controller.memorymanagement;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Heap {
    private int memSize = 50;
    private boolean[] memTable = new boolean [memSize];
    private Proc[] memory = new Proc[memSize];
    private int size;
    
    private final Lock lock = new ReentrantLock();
    private final Condition reading = lock.newCondition();
    private final Condition writing = lock.newCondition();
    
    private boolean read = false, write = false;
    
    
    public Heap(){
        
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
