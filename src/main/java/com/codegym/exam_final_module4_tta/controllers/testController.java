package com.codegym.exam_final_module4_tta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class testController {

    @GetMapping("")
    public String display(){
        return "test/list";
    }
}
