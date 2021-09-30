package proj.esso.MemManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proj.esso.MemManagement.controller.memorymanagement.sync.MemoryManagement;

@RestController
@RequestMapping("/api")
public class Index {

    @GetMapping("/sync")
    public String index(
        @RequestParam(name = "minSize", defaultValue = "-1") int minSize,
        @RequestParam(name = "maxSize", defaultValue = "-1") int maxSize,
        @RequestParam(name = "minTime", defaultValue = "-1") int minTime,
        @RequestParam(name = "maxTime", defaultValue = "-1") int maxTime,
        @RequestParam(name = "numReq", defaultValue = "-1") int numReq,
        @RequestParam(name = "quantum", defaultValue = "-1") int quantum,
        @RequestParam(name = "heapSize", defaultValue = "-1") int heapSize,
        @RequestParam(name = "heapThreshold", defaultValue = "-1") double heapThreshold
    )
    {
        if(minSize == -1 || maxSize == -1 || minTime == -1 || maxTime == -1 || numReq == -1 || quantum == -1 || heapSize == -1 || heapThreshold == -1)
        {
            return "Error: Invalid Parameters";
        }
        MemoryManagement memManagement = new MemoryManagement(maxSize, minSize, maxTime, minTime, numReq, quantum, heapSize, heapThreshold, 1 );
        memManagement.run();

        return memManagement.getLog();

    }
}
