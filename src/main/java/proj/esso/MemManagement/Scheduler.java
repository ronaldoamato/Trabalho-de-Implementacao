
package memorymanagement;

import java.util.logging.Level;
import java.util.logging.Logger;

//class Scheduler implements Runnable{
class Scheduler{
    PCB set_Process = new PCB();
    Heap alloc_process = new Heap();
    
    public void vai(PCB get_Process){
        
    get_Process.printerReady();
    Process process = get_Process.getProcess(0);
    
    while (process != null){
        
        Core execute = new Core();
        
        alloc_process.alloc(process);                   //faz a alocação em memoria
        get_Process.changeQueue(process, 1);            //altera para fila running
       
        get_Process.printerRunning();
        try {
            execute.exec(process);                          //executa o processo
            get_Process.printerRunning();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        if (process.getExecTime() > 0){                 //Se o processo não terminou, volta para a fila ready
            
            if (process.getPriority() == 5){            //Se o processo tem alta prioridade, será selecionado primeiro na fila.
                set_Process.addProcess(process);
            } 
            get_Process.changeQueue(process, 0);
        }
        else {
            get_Process.changeQueue(process, 2);        //Se o processo finalizou o tempo de execução, vai pra fila de terminated.
            //get_Process.printerTerminated();
        }
        
        process = get_Process.getProcess(0);            //busca o próximo processo da fila ready
    }
  }   

    
}
