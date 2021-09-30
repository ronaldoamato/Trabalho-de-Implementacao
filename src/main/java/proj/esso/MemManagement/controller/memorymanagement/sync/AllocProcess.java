
package proj.esso.MemManagement.controller.memorymanagement.sync;

class AllocProcess extends Alloc {
    
    private Proc process;
    private int uses;

    public AllocProcess(int start, int offset, Proc process)
    {
        super(start, offset);
        this.process = process;
        this.uses = 0;

    }

    Proc getProcess() {
        return this.process;
    }

    int getUses(){return this.uses;}
    void setUses(int uses){this.uses = uses;}


    void setProcess(Proc process) {
        this.process = process;
    }

    
}
