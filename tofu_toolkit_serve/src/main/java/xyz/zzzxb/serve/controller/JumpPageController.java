package xyz.zzzxb.serve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * zzzxb
 * 2024/3/28
 */
@Controller
public class JumpPageController {


    @GetMapping("/jump/{path}")
    public String jump(@PathVariable("path") String path) {
        return "pages/" + path;
    }
}
