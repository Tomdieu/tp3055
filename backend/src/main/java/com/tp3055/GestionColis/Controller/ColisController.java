package com.tp3055.GestionColis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp3055.GestionColis.Service.ColisService;

import com.tp3055.GestionColis.Model.Entity.Colis;

@RestController
@RequestMapping(path = "api/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @PostMapping
    public Object addColis(@RequestBody Colis colis) {
        return colisService.save(colis);
    }

    @GetMapping("/find/{code}")
    public Colis getColisByCode(@PathVariable String code) {
        return colisService.findByCode(code).orElse(null);
    }

    @GetMapping("/{town}")
    public List<Colis> listColis(@PathVariable String town) {
        return colisService.listColis(town);
    }

    @PostMapping("/send/{colisId}/{userId}")
    public Colis sendColis(@PathVariable long colisId,@PathVariable long userId){
        return colisService.sendColis(colisId,userId);
    }

    @PostMapping("/arrive/{colisId}/{userId}")
    public Colis arriveColis(@PathVariable long colisId,@PathVariable long userId){
        return colisService.arriveColis(colisId,userId);
    }

    @PostMapping("/withdraw/{colisId}/{userId}")
    public Colis withdrawColis(@PathVariable long colisId,@PathVariable long userId){
        return colisService.withdrawColis(colisId,userId);
    }

    @GetMapping("/all")
    public List<Colis> listColis(@RequestParam String from_date, @RequestParam String to_date,
            @RequestParam(required = false) String state) {

        if (state != null) {
            return colisService.listColisBetweenDate(from_date, to_date, state);
        }

        return colisService.listColisBetweenDate(from_date, to_date);

    }
}