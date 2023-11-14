package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringController {
    @ResponseBody
    @GetMapping("/toUnderLineLowerCase")
    public String toUnderLineLowerCase(String msg){
        return StringUtil.toUnderLineLowerCase(msg);
    }
    @ResponseBody
    @GetMapping("/toUppercase")
    public String toUppercase(String msg){
        return StringUtil.toUpperCase(msg);
    }
}
