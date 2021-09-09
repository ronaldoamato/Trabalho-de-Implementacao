
package memorymanagement;

import java.util.Random;


public class RandomProcessGenerator {
    private int minSize;
    private int maxSize;
    private PCB set_Process;
    
    public RandomProcessGenerator(int minSize, int maxSize)
    {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
    
    public void newProcess()
    {
        Random random = new Random();
        int memSize = random.nextInt(maxSize) + minSize; //Gera um valor aleatório entre o mínimo e máximo especificado
        int time = random.nextInt(100)+ 1;               //Gera o tempo de execução do necessário para conclusão do processo.
        String id = Integer.toString(random.hashCode()); //Gera a id do objeto, com o hashcode.
        int priority = random.nextInt(4)+ 1;             //Gera um valor de prioridade entre 1 e 5.
        
        Process process = new Process(memSize, time, id, priority);
        set_Process.addProcess(process);
    }

}
