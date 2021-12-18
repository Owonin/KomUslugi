package com.example.komUslugi.controller;

import com.example.komUslugi.data.dao.ServicesDao;
import com.example.komUslugi.data.dao.ZpDao;
import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.Oplata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController {
    @Autowired
    ServicesDao servicesDao;
    @Autowired
    ZpDao zpDao;

    @RequestMapping({"", "/index"})
    public String MainPage() {
        return "mainpage";
    }

    @GetMapping("/oplata")
    public String Oplata(Model model) {
        model.addAttribute("servicesList", servicesDao.findQAll())
                .addAttribute("housesList", zpDao.findQAll());

        return "oplata";
    }

    @GetMapping("/services")
    public String Services() {
        return "tovary_uslugi";
    }

    @GetMapping("/services/{sId}")
    public String Services(@PathVariable("sId")Long id,
                           Model model) {
        Komm_uslugi us = servicesDao.getById(id);
        model.addAttribute("res", us.getEdinitsaIzmereniya()).addAttribute("name", us.getVidUslug());
        
        return "tovary_uslugi";
    }

    @GetMapping("/admin")
    public String AdminPdg() {
        return "admin";
    }

    @PostMapping("/oplata")
    public String OplataData(@ModelAttribute Oplata oplata) {
        log.info(oplata.toString());
        return "redirect:/";

    }
}
