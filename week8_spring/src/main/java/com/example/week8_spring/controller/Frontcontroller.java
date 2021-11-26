package com.example.week8_spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Frontcontroller {
    @GetMapping("/view")
    public String getViewpage(){

        return "view";
//        return "redirect:https://www.sendkite.shop/";
    }
}
