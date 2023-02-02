package com.tp3055.GestionColis.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp3055.GestionColis.Service.ColisService;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.State;


@RestController
@RequestMapping(path = "api/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @GetMapping("/")
    public List<Colis> listAllColis(){
        return colisService.getAll();
    }

    @PostMapping("/add/{userId}")
    public Object addColis(@RequestBody Colis colis,@PathVariable Long userId) {
        return colisService.save(colis,userId);
    }

    @GetMapping("/{id}/")
    public Colis getClisById(Long id){
        return colisService.findById(id);
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
    public List<Colis> listColis(@RequestParam Date from_date, @RequestParam Date to_date,
            @RequestParam(required = false) State state) {
            System.out.println(from_date+" "+to_date);
        if (state != null) {

            return colisService.listColisBetweenDate(from_date, to_date, state);
        }

        return colisService.listColisBetweenDate(from_date, to_date);

    }
}