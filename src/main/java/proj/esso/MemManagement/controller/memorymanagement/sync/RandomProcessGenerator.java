
package proj.esso.MemManagement.controller.memorymanagement.sync;

import java.util.Random;
import java.util.logging.Level;

class RandomProcessGenerator{
    private Random random;

    public RandomProcessGenerator()
    {
        this.random = new Random();
    }
    
    Proc gen(int minSize, int maxSize, int minTime, int maxTime)
    { 
        Proc process = new Proc(
        random.nextInt(maxSize) + minSize,
        random.nextInt(maxTime) + minTime
        );

        process.setId(process.toString());

        return process;
    }

}
