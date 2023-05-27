package tads.ufrn.provapw2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.service.FrutaService;

import java.util.List;

@Controller
public class FrutaController {
    FrutaService frutaService;
    public FrutaController(FrutaService frutaService) {
        this.frutaService = frutaService;
    }
    @RequestMapping(value = {"/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String getIndex(Model model) {

        List<Fruta> ListarFrutas = frutaService.listarFrutas();
        model.addAttribute("ListarFrutas", ListarFrutas);
        return "index.html";
    }




}

