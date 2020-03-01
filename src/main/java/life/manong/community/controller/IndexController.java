package life.manong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/greeting")
    public String index(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }
}
