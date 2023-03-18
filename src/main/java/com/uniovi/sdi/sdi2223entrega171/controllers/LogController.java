package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;

@Controller
public class LogController {

    @Autowired
    private LogService logService;



    @RequestMapping("/log/list")
    public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required = false) String typeToFilter){
        Page<Log> logs = new PageImpl<Log>(new LinkedList<Log>());
        if(typeToFilter != null && !typeToFilter.isEmpty()) {
            logs = logService.searchByType(pageable, typeToFilter);
        } else {
            logs = logService.getLogs(pageable);
        }

        model.addAttribute("typesList", logService.getTypesLog());
        model.addAttribute("logsList", logs.getContent());
        model.addAttribute("page", logs);

        return "log/list";
    }

    @RequestMapping("/log/list/update")
    public String updateList(Model model, Pageable pageable,Principal principal) {

        model.addAttribute("typesList", logService.getTypesLog());
        model.addAttribute("logsList", logService.getLogs(pageable).getContent());

        return "log/list :: tableLogs";
    }

    @RequestMapping("/log/deleteAll")
    public String deleteAll() {
        logService.deleteAll();
        return "redirect:/log/list";
    }


}
