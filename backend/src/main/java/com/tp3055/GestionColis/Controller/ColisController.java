package com.tp3055.GestionColis.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp3055.GestionColis.Service.ColisService;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Expedition;
import com.tp3055.GestionColis.Model.Entity.Retrait;
import com.tp3055.GestionColis.Model.Entity.State;

@RestController
@RequestMapping(path = "api/colis")
@CrossOrigin(origins = "*")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @GetMapping("/")
    public List<Colis> listAllColis() {
        return colisService.getAll();
    }

    @PostMapping("/add/{userId}")
    public Colis addColis(@RequestBody Colis colis, @PathVariable Long userId) {
        System.out.println("colis : "+colis);
        return colisService.save(colis, userId);
    }

    @GetMapping("/{id}/")
    public Colis getClisById(Long id) {
        return colisService.findById(id);
    }

    @GetMapping("/find/{code}")
    public Colis getColisByCode(@PathVariable String code) {
        return colisService.findByCode(code).orElse(null);
    }

    @GetMapping("/list/{town}/")
    public List<Colis> listColis(@PathVariable String town) {
        return colisService.listColis(town);
    }

    @PostMapping("/send/{colisId}/{userId}")
    public Colis sendColis(@PathVariable long colisId, @PathVariable long userId) {
        return colisService.sendColis(colisId, userId);
    }

    @PostMapping("/arrive/{colisId}/{userId}")
    public Colis arriveColis(@PathVariable long colisId, @PathVariable long userId) {
        return colisService.arriveColis(colisId, userId);
    }

    @PostMapping("/withdraw/{colisId}/{userId}")
    public Colis withdrawColis(@PathVariable long colisId, @PathVariable long userId) {
        return colisService.withdrawColis(colisId, userId);
    }

    @GetMapping("/all")
    public List<Colis> listColis(@RequestParam Date from_date, @RequestParam Date to_date,
            @RequestParam(required = false) State state) {
        System.out.println(from_date + " " + to_date);
        if (state != null) {

            return colisService.listColisBetweenDate(from_date, to_date, state);
        }

        return colisService.listColisBetweenDate(from_date, to_date);

    }

    @GetMapping("/filter/{town}")
    public List<Colis> listColis(@RequestParam Date from_date, @RequestParam Date to_date,
            @RequestParam(required = false) State state, @PathVariable String town) {
        System.out.println(from_date + " " + to_date);
        if (state != null) {

            return colisService.listColisBetweenDate(town, from_date, to_date, state);
        }

        return colisService.listColisBetweenDate(town, from_date, to_date);

    }

    @GetMapping(value = "/{colisId}/expedition")
    public Expedition getColisExpedionDetail(@PathVariable Long colisId) {
        return colisService.getColisExpeditionDetail(colisId);
    }

    @GetMapping(value = "/{colisId}/retrait")
    public Retrait getColisRetraitDetail(@PathVariable Long colisId) {
        return colisService.getColisRetraitDetail(colisId);
    }

    @GetMapping(value = "/arriver/{town}")
    public List<Retrait> getColisArrive(@PathVariable String town) {
        return colisService.getColisArrive(town);
    }

    @GetMapping(value = "/withdraw/{town}")
    public List<Retrait> getColisWithdraw(@PathVariable String town) {
        return colisService.getColisWithdraw(town);
    }

    @GetMapping(value = "/pending/{town}")
    public List<Expedition> getColisPending(@PathVariable String town) {
        return colisService.getColisPending(town);
    }

    @GetMapping(value = "/transfer/{town}")
    public List<Expedition> getColisTransfer(@PathVariable String town) {
        return colisService.getColisSend(town);
    }

}