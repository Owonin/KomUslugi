package com.example.komUslugi.controller;

import com.example.komUslugi.data.dao.OplataDao;
import com.example.komUslugi.data.dao.QueryDao;
import com.example.komUslugi.data.dao.ServicesDao;
import com.example.komUslugi.data.dao.ZpDao;
import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.Oplata;
import com.example.komUslugi.data.domain.Querys;
import com.example.komUslugi.data.domain.Zhiloe_pomeshenie;
import com.example.komUslugi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Slf4j
@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    ZpDao zpDao;
    @Autowired
    OplataDao oplataDao;
    @Autowired
    QueryDao queryDao;


    @RequestMapping({"", "/index"})
    public String MainPage() {
        return "mainpage";
    }

    @GetMapping("/oplata")
    public String Oplata(Model model, Principal principal) {

        ArrayList<Oplata> oplatas = (ArrayList<Oplata>) oplataDao.findByUndone(userService.findByLogin(principal.getName()).getZhilets().getKod_zhiltsa())
                .orElse(null);
        ArrayList<Komm_uslugi> uslugi = new ArrayList<>();
        if (oplatas.size() == 0) {
            return "redirect:/";
        }
        int count = 0;
        LocalDate d = oplatas.get(0).getKrainiySrokOplati();
        LocalDate n = LocalDate.now();
        int a = d.compareTo(LocalDate.now());
        for (Oplata oplata : oplatas) {
            if (oplata.getKrainiySrokOplati().compareTo(LocalDate.now()) < 0)
                count++;
            uslugi.add(oplata.getUsluga());
        }
        Oplata last = oplatas.get(0);
        oplatas.clear();
        oplatas = (ArrayList<Oplata>) oplataDao.findByUndoneByParam(last.getUsluga().getKod_uslugi()
                ,last.getZhiloe_pomeshenie().getKod_zhilogo_pomesheniya()
                ,userService.findByLogin(principal.getName()).getZhilets().getKod_zhiltsa()).get();
        int penny =0;
        for (Oplata oplata : oplatas) {
            if (oplata.getKrainiySrokOplati().compareTo(LocalDate.now()) < 0){
                penny+= 10000 * 7.5/300 * Period.between(oplata.getKrainiySrokOplati(),LocalDate.now()).getDays();
            }
        }

        if (count > 0)
            model.addAttribute("prosrochka", count);

        Zhiloe_pomeshenie zp = last.getZhiloe_pomeshenie();
        zp.setAdressDomaZhPom(zp.getGorodZhPom()+" "+zp.getAdressDomaZhPom()+" "+ zp.getNomerDomaZhPom());

        model.addAttribute("service", last.getUsluga())
                .addAttribute("house", zp)
                .addAttribute("summa", last.getUsluga().getStoimostUslug())
                .addAttribute("penny", penny);

        return "oplata";
    }

    @GetMapping("/admin")
    public String AdminPdg() {
        return "admin";
    }

    @PostMapping("/oplata")
    public String OplataData(@RequestParam int SummaOplati,
                             @RequestParam Long usluga,
                             @RequestParam Long zhiloe_pomeshenie,
                             Principal principal) {

        ArrayList<Oplata> oplatas = (ArrayList<Oplata>) oplataDao.findByUndoneByParam(usluga
                ,zhiloe_pomeshenie
                ,userService.findByLogin(principal.getName()).getZhilets().getKod_zhiltsa()).get();

        for(Oplata oplata: oplatas){
            oplata.setSostoyanie_oplati(true);
        }
        Oplata oplata = oplatas.get(oplatas.size() - 1);
        oplata.setSummaKOplate(SummaOplati);
        oplata.setDataOplati(LocalDate.now());

        Querys q = new Querys();
        q.setUser(userService.findByLogin(principal.getName()));
        q.setQuery("Ordered: "+ oplata.toString());
        queryDao.saveAndFlush(q);


        return "redirect:/";

    }

    @GetMapping("/otchet")
    public String Zapros(Model model, Principal principal) {
        ArrayList<Oplata> oplatas = (ArrayList<Oplata>) oplataDao.findByDone(userService.findByLogin(principal.getName()).getZhilets().getKod_zhiltsa()).orElse(null);
        model.addAttribute("oplatas", oplatas);

        return "zapros";
    }

}
