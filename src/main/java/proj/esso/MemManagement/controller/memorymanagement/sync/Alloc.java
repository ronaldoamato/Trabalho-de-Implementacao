package proj.esso.MemManagement.controller.memorymanagement.sync;

class Alloc {

    private int start;
    private int offset;

    public Alloc(int start, int offset)
    {
        this.start = start;
        this.offset = offset;
    }

    int getStart(){return this.start;}
    void setStart(int start){this.start = start;}
    int getOffset(){return this.offset;}
    void setOffset(int offset){this.offset = offset;}
}
