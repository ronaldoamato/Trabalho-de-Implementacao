
package memorymanagement;

import java.util.Random;


public class RandomProcessGenerator {
    private int minSize;
    private int maxSize;
    
    public RandomProcessGenerator(int minSize, int maxSize)
    {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
    
    public void newProcess()
    {
        Random random = new Random();
        int memSize = random.nextInt(maxSize) + minSize;
        int time = random.nextInt(100)+ 1;
        String id = Integer.toString(random.hashCode());
        
        Process process = new Process(memSize, time, id);
    }

}
