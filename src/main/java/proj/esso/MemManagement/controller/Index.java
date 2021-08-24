package proj.esso.MemManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class Index {

    @GetMapping("/")
    public String index(@RequestParam(name = "param", defaultValue = "jorje") String param) {
        return String.format("input = %s", param);
    }
}
