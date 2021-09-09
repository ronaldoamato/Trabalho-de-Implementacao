
package memorymanagement;

class Scheduler {
    static AllocProcess alloc;
    static PCB set_Process;
    
    public static void main(String args[]){
        
    PCB get_Process = null;
    Process process = get_Process.getProcess(0);
    
    while (process != null){
        alloc.setProcess(process);
        get_Process.changeQueue(process, 1);
        
        /*
        atribui o processo para a cpu
        */ 
        
        if (process.getExecTime() > 0){
            
            if (process.getPriority() == 5){
                set_Process.addProcess(process);
            } 
            get_Process.changeQueue(process, 0);
        }
        else {
            get_Process.changeQueue(process, 2);
        }
        
        process = get_Process.getProcess(0);
    }
  }   
}
