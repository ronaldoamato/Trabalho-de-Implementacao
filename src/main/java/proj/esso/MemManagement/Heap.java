
package memorymanagement;

import java.util.Arrays;


public class Heap {
    private int memSize = 128;
    private boolean[] memory = new boolean [memSize];
    private int size;
    
    public Heap(){
        this.size = size;
    }

    public boolean[] getMemory() {
        return memory;
    }

    public int getSize() {
        return this.size;
    }
    
    public void alloc(Process process){ //Algoritmo first fit
        int count = 0;
        for (int i=0; i<memSize; i++) {
            if (memory[i]==false)
            {
                for (int j = 0; j<process.getMemSize(); j++){
                    count++;
                }
                if (count == process.getMemSize()){
                    for (int k=i; k<process.getMemSize(); k++){
                        memory[k]=true;
                    }
                }   
            }
        }
    }
    
    
    
}
