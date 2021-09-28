
package proj.esso.MemManagement.controller.memorymanagement;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class RandomProcessGenerator{
    private Random random;
    private int minSize;
    private int maxSize;
    private int numReq;

    public RandomProcessGenerator(int minSize, int maxSize, int numReq)
    {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.numReq = numReq;
        this.random = new Random();
    }
    
    public Proc gen(int minSize, int maxSize, int numReq)
    { 
        while (numReq > 0){
            numReq--;

            int memSize = random.nextInt(maxSize) + minSize; //Gera um valor aleatório entre o mínimo e máximo especificado
            int time = random.nextInt(20)+ 1;                //Gera o tempo de execução do necessário para conclusão do processo.
            String id = Integer.toString(random.hashCode()); //Gera a id do objeto, com o hashcode.
            int priority = random.nextInt(4)+ 1;             //Gera um valor de prioridade entre 1 e 5.

            return new Proc(memSize, time, id, priority); //Gera novo processo.
            //try {
                //set_Process.addProcess(process);             //Adiciona novo processo na fila Ready
            //} catch (InterruptedException ex) {
                //Logger.getLogger(RandomProcessGenerator.class.getName()).log(Level.SEVERE, null, ex);
            //}
                                                  //Decrementa do total de processos
        }
//        try {
//            set_Process.printerReady();
//            play.vai(set_Process);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(RandomProcessGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        set_Process.printerTerminated();
    }
}
