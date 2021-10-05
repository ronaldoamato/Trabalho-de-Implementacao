
package sync;

import java.util.Random;

class RandomProcessGenerator{
    private Random random;

    public RandomProcessGenerator()
    {
        this.random = new Random();
    }
    
    Proc gen(int minSize, int maxSize, int minTime, int maxTime, int id)
    { 
        Proc process = new Proc(
        random.nextInt(maxSize) + minSize,
        random.nextInt(maxTime) + minTime
        );

        process.setId(Integer.toString(id));

        return process;
    }

}
