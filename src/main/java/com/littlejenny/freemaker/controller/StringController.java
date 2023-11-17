package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class StringController {
    @ResponseBody
    @GetMapping("/toNotCamel")
    public String toUnderLineLowerCase(String msg){
        return StringUtil.toNotCamel(msg);
    }
    @ResponseBody
    @GetMapping("/toCamel")
    public String toUppercase(String msg){
        return StringUtil.toCamel(msg);
    }
}
