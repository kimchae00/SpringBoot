package kr.co.farmstory.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping(value = {"", "index"})
    public String index(Principal principal, Model model, String group, @Param("cate") String cate){

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "index";

    }
}
