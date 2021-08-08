
package memorymanager;

public class Pool implements Runnable {
    
    public Integer count;

    public Pool(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        
        System.out.println(Thread.currentThread().getName()+" start : " + count);
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        
        System.out.println(Thread.currentThread().getName()+" end : " + count);
    }

    @Override
    public String toString() {
        return "Pool{" + "count=" + count + '}';
    }
    
    
    
}
