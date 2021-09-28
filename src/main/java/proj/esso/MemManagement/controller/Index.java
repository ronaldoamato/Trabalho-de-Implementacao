package proj.esso.MemManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.esso.MemManagement.controller.memorymanagement.MemoryManagement;

@RestController
@RequestMapping("/api")
public class Index {

    @GetMapping("/")
    public String index(
        @RequestParam(name = "minSize", defaultValue = "-1") int minSize,
        @RequestParam(name = "maxSize", defaultValue = "-1") int maxSize,
        @RequestParam(name = "numReq", defaultValue = "-1") int numReq
    )
    {
        MemoryManagement memManagement = new MemoryManagement(maxSize, minSize, numReq);
        memManagement.run();

        return memManagement.getLog();

    }
}
