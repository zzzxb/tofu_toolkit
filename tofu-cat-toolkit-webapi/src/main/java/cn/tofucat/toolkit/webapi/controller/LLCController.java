package cn.tofucat.toolkit.webapi.controller;

import cn.tofucat.toolkit.webapi.service.LLCService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/llc")
public class LLCController {

    private final LLCService llCService;

    public LLCController(LLCService llCService) {
        this.llCService = llCService;
    }

    @PostMapping("/exec")
    public String exec(@RequestBody String llcCode) {
        return llCService.exec(llcCode);
    }
}
