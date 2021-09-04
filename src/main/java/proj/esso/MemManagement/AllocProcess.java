
package memorymanagement;


public class AllocProcess {
    
    private Process process;
    private int start;
    private int end;

    public Process getProcess() {
        return process;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
}
