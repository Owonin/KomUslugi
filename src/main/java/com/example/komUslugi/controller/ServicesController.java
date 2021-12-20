package com.example.komUslugi.controller;

import com.example.komUslugi.data.dao.OplataDao;
import com.example.komUslugi.data.dao.QueryDao;
import com.example.komUslugi.data.dao.ServicesDao;
import com.example.komUslugi.data.dao.ZpDao;
import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.Oplata;
import com.example.komUslugi.data.domain.Querys;
import com.example.komUslugi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    ServicesDao servicesDao;
    @Autowired
    QueryDao queryDao;
    @Autowired
    ZpDao zpDao;
    @Autowired
    OplataDao oplataDao;
    @Autowired
    UserService userService;



    @GetMapping("")
    public String Services() {
        return "tovary_uslugi";
    }

    @GetMapping("/{Kod_uslugi}")
    public String Services(@PathVariable("Kod_uslugi")Long id,
                           Model model) {
        Komm_uslugi us = servicesDao.findByKod_uslugi(id);
        model.addAttribute("service", us).addAttribute("housesList", zpDao.findQAll());

        return "serviceOplata";
    }

    @PostMapping("/{Kod_uslugi}")
    public String ServicesData(@PathVariable("Kod_uslugi") Komm_uslugi kommUslugi,
                               @ModelAttribute Oplata oplata,
                               Principal principal) {
        oplata.setUsluga(kommUslugi);
        oplata.setSummaKOplate(kommUslugi.getStoimostUslug());
        oplata.setKrainiySrokOplati(LocalDate.now());
        oplata.setDataOplati(LocalDate.now());
        oplata.setZhilets(userService.findByLogin(principal.getName()).getZhilets());
        oplata.setSostoyanie_oplati(true);
        oplataDao.save(oplata);

        Querys q = new Querys();
        q.setQuery("Ordered: "+ oplata.toString());
        queryDao.saveAndFlush(q);

        return "mainpage";
    }

}
