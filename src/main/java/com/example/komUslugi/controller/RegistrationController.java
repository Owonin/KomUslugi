package com.example.komUslugi.controller;

import com.example.komUslugi.data.dao.ServicesDao;
import com.example.komUslugi.data.dao.UpravOrgDao;
import com.example.komUslugi.data.dao.ZpDao;
import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.UprOrganizatsiya;
import com.example.komUslugi.data.domain.Zhilets;
import com.example.komUslugi.data.domain.Zhiloe_pomeshenie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    ZpDao zpDao;
    @Autowired
    UpravOrgDao uoDao;
    @Autowired
    ServicesDao servicesDao;



    @GetMapping("/user")
    public String userRegistration(Model model) {
        model.addAttribute("housesList", zpDao.findQAll());
        return "userReg";
    }

    @PostMapping("/user")
    public String OplataData(@ModelAttribute Zhilets zhilets,
                             @RequestParam String name,
                             @RequestParam String Sname,
                             @RequestParam String patron){
        log.info(" "+zhilets.getElectronnayaPochta());
        log.info(name+Sname+patron);
        return "redirect:/admin";
    }

    @GetMapping("/house")
    public String ZpRegistration() {
        return "house";
    }

    @PostMapping("/house")
    public String ZpData(@ModelAttribute Zhiloe_pomeshenie zp,
                         @RequestParam String gorodZhPom,
                         @RequestParam String ylitsaZhPom,
                         @RequestParam String nomerDomaZhPom) {
        zp.setAdressDomaZhPom(gorodZhPom+" "+ylitsaZhPom+" "+ nomerDomaZhPom);
        zpDao.save(zp);
        return "redirect:/admin";
    }

    @GetMapping("/org")
    public String UrRegistration() {
        return "orgReg";
    }

    @PostMapping("/org")
    public String UrData(@ModelAttribute UprOrganizatsiya uo,
                         @RequestParam String gorodOrg,
                         @RequestParam String ulitsaOrg,
                         @RequestParam String nomerZdaniyaOrg){
        //zpDao.save(zp);
        uo.setAdressOrg(gorodOrg+" "+ulitsaOrg+" "+nomerZdaniyaOrg);
        uoDao.save(uo);
        return "redirect:/admin";
    }

    @GetMapping("/services")
    public String ServicesRegistration() {
        return "servicesReg";
    }

    @PostMapping("/services")
    public String ServicesData(@ModelAttribute Komm_uslugi kommUslugi){
        servicesDao.save(kommUslugi);
        return "redirect:/admin";
    }
}
