package top.wujinxing.marshome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {

    @RequestMapping(value = {"/","/index.html"})
    public String index() {
        return "index";
    }
    
}
