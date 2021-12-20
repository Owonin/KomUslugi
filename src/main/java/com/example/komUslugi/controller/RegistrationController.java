package com.example.komUslugi.controller;

import com.example.komUslugi.data.dao.*;
import com.example.komUslugi.data.domain.*;
import com.example.komUslugi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

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
    @Autowired
    ZhiletsDao zhiletsDao;
    @Autowired
    UserService userService;
    @Autowired
    QueryDao queryDao;
    @Autowired
    OplataDao oplataDao;



    @GetMapping("/user")
    public String userRegistration(Model model) {
        model.addAttribute("housesList", zpDao.findQAll());
        return "userReg";
    }

    @PostMapping("/user")
    public String OplataData(@ModelAttribute Zhilets zhilets,
                             @RequestParam String name,
                             @RequestParam String Sname,
                             @RequestParam String patron,
                             @RequestParam String login,
                             @RequestParam String psw,
                             Principal principal){
        zhilets.setFIO(name+" "+Sname+" "+patron );

        User user = new User();
        user.setPassword(psw);
        user.setLogin(login);
        user.setZhilets(zhilets);
        userService.saveUser(user);

        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Registered: "+ zhilets.toString());
        queryDao.saveAndFlush(q);


        return "redirect:/admin";
    }

    @GetMapping("/house")
    public String ZpRegistration() {
        return "house";
    }

    @PostMapping("/house")
    public String ZpData(@ModelAttribute Zhiloe_pomeshenie zp,
                         Principal principal) {
        zpDao.saveAndFlush(zp);

        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Registered: "+ zp.toString());
        queryDao.saveAndFlush(q);



        return "redirect:/admin";
    }

    @GetMapping("/org")
    public String UrRegistration() {
        return "orgReg";
    }

    @PostMapping("/org")
    public String UrData(@ModelAttribute UprOrganizatsiya uo,
                         Principal principal){


        uoDao.saveAndFlush(uo);

        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Registered: "+ uo.toString());
        queryDao.saveAndFlush(q);

        return "redirect:/admin";
    }

    @GetMapping("/services")
    public String ServicesRegistration() {
        return "servicesReg";
    }

    @PostMapping("/services")
    public String ServicesData(@ModelAttribute Komm_uslugi kommUslugi, Principal principal){
        servicesDao.save(kommUslugi);


        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Registered: "+ kommUslugi.toString());
        queryDao.saveAndFlush(q);


        return "redirect:/admin";
    }

    @GetMapping("/oplata")
    public String OplataRegistration(Model model) {
        model.addAttribute("servicesList", servicesDao.findQAll().stream().skip(6).toArray());
        return "oplataReg";
    }

    @PostMapping("/oplata")
    public String OplataData(@ModelAttribute Oplata oplata,
                             @RequestParam Long id,
                             @RequestParam Long idzp,
                             @RequestParam String SrokOplati,
                             Principal principal){

        LocalDate date = LocalDate.parse(SrokOplati);
        oplata.setKrainiySrokOplati(date);
        oplata.setSostoyanie_oplati(false);

        Zhilets zhilets = zhiletsDao.findByKod(id);
        oplata.setZhilets(zhilets);
        oplata.setZhiloe_pomeshenie(zpDao.getById(idzp));
        oplataDao.saveAndFlush(oplata);


        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Registered: "+ oplata.toString());
        queryDao.saveAndFlush(q);

        return "redirect:/admin";
    }
}
