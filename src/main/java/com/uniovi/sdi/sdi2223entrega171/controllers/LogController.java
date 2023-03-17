package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/log/list")
    public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required = false) String type){

        return "log/list";
    }


    @RequestMapping("/log/list/update")
    public String updateList(Model model, Pageable pageable,Principal principal) {
        return "log/list :: tableMarks";
    }


}
