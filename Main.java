import sync.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws NoProcessException, InterruptedException, IOException
    {
        MemoryManagement mem = new MemoryManagement(10, 1, 100, 20, 100, 10, 256, 0.6, 1, 0.2);

        mem.run();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(mem.getLog());
        writer.close();

    }
}
