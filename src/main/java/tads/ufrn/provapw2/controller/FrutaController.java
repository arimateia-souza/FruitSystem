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
    @GetMapping("/cadastro")
    public String getCadastrarPage(Model model) {
        Fruta f = new Fruta();
        model.addAttribute("fruta", f);
        return "cadastrarPage.html";
    }
    @PostMapping("/salvar")
    public String doSalvar(@ModelAttribute @Valid Fruta f, Errors errors){
        if (errors.hasErrors()){
            return "cadastrarPage";
        }else{
            frutaService.salvarFruta(f);
            return "redirect:/index";
        }
    }

    @GetMapping(value = "/deletar/{id}")
    public String deletarFruta(@PathVariable long id){
        frutaService.deletarFruta(id);
        return "redirect:/index";
    }
    @GetMapping("/editar/{id}")
    public String getEditarPage(@PathVariable(name = "id") Long id, Model model){

        Optional<Fruta> f = frutaService.buscarFrutaPorId(id);

        if (f.isPresent()){
            model.addAttribute("fruta", f.get());
        }else{
            return "redirect:/index";
        }

        return "editarPage";
    }


}

