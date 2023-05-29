package tads.ufrn.provapw2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.service.FrutaService;

import java.util.List;
import java.util.Optional;

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
        //Cookie cookie = new Cookie("carrinhoCompras","");
       // cookie.setMaxAge(60*60*24);
        //model.addCookie(cookie);
        return "index.html";
    }



}

