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
    public String toNotCamel(String msg){
        return StringUtil.toNotCamel(msg);
    }
    @ResponseBody
    @GetMapping("/toCamel")
    public String toCamel(String msg){
        return StringUtil.toCamel(msg);
    }
    @ResponseBody
    @GetMapping("/toUpper")
    public String toUpper(String msg){
        return msg.toUpperCase();
    }
    @ResponseBody
    @GetMapping("/toLower")
    public String toLower(String msg){
        return msg.toLowerCase();
    }
}
