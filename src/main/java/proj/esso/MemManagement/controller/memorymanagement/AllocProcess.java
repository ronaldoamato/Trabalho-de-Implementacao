
package proj.esso.MemManagement.controller.memorymanagement;


public class AllocProcess {
    
    private Proc process;
    private int start;
    private int end;

    public Proc getProcess() {
        return process;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setProcess(Proc process) {
        this.process = process;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
}
