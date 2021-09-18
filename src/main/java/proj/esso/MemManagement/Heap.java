
package memorymanagement;

import java.util.Arrays;


public class Heap {
    private int memSize = 128;
    private boolean[] memTable = new boolean [memSize];
    private Process[] memory = new Process[memSize];
    private int size;
    //private AllocProcess alloc = null;
    
    public Heap(){
        
    }
    
    public Heap(int size){
        this.size = size;
    }

    public boolean[] getMemory() {
        return memTable;
    }

   /* public int getSize() {
        return this.size;
    }*/
    
    public void alloc(Process process){ //Algoritmo first fit
        int count = 0;
        for (int i=0; i<memSize; i++) {
            if (memTable[i]==false)
            {
                for (int j = 0; j<process.getMemSize(); j++){
                    if (memTable[j]==false){
                        count++;
                    }
                }
                if (count == process.getMemSize()){
                    for (int k=i; k<process.getMemSize(); k++){
                        memTable[k]=true;
                        memory[k]= process;
                        
                    }
                    count = 0;
                    //alloc.setProcess(process);
                    //alloc.setStart(i);
                    //alloc.setEnd(i+process.getMemSize());
                }   
            }
        }
    }
    
    
    
}
