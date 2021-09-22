
package memorymanagement;

import java.util.logging.Level;
import java.util.logging.Logger;

//class Scheduler implements Runnable{
class Scheduler{
    
    
    PCB set_Process = new PCB();
    
    public void vai(PCB get_Process) throws InterruptedException{

    Process process = get_Process.getProcess(0);
    Heap alloc_process = new Heap();
    Core execute = new Core();

    while (process != null){
        alloc_process.alloc(process);                   //faz a alocação em memoria
        get_Process.changeQueue(process, 1);            //altera para fila running
       
        try {
            execute.exec(process);                          //executa o processo
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        if (process.getExecTime() > 0){                 //Se o processo não terminou, volta para a fila ready
            
            /*if (process.getPriority() == 5){            //Se o processo tem alta prioridade, será selecionado primeiro na fila.
                this.set_Process.addProcess(process);
            }*/ 
            get_Process.changeQueue(process, 0);
        }
        else {
            get_Process.changeQueue(process, 2);        //Se o processo finalizou o tempo de execução, vai pra fila de terminated.
            //alloc_process.desalloc(process);
        }
        
        process = get_Process.getProcess(0);            //busca o próximo processo da fila ready
    }
    //System.out.println("Memoria Desalocada: ");
    alloc_process.printMemory();
  }   

    
}
