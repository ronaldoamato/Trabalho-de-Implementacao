
package memorymanagement;

class Scheduler {
    //static AllocProcess alloc;
    static PCB set_Process;
    static Heap alloc_process;
    
    public static void main(String args[]){
        
    PCB get_Process = null;
    Process process = get_Process.getProcess(0);
    
    while (process != null){
        alloc_process.alloc(process); //faz a alocação em memoria
        get_Process.changeQueue(process, 1);
        
        /*
        atribui o processo para a cpu
        */ 
        
        if (process.getExecTime() > 0){ //Se o processo não terminou, volta para a fila ready
            
            if (process.getPriority() == 5){ //Se o processo tem alta prioridade, será selecionado primeiro na fila.
                set_Process.addProcess(process);
            } 
            get_Process.changeQueue(process, 0);
        }
        else {
            get_Process.changeQueue(process, 2); //Se o processo finalizou o tempo de execução, vai pra fila de terminated.
        }
        
        process = get_Process.getProcess(0);
    }
  }   
}
