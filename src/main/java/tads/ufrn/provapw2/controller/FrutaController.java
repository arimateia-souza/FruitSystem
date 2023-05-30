package tads.ufrn.provapw2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.model.Usuario;
import tads.ufrn.provapw2.service.FrutaService;
import tads.ufrn.provapw2.service.UsuarioService;

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

        List<Fruta> frutas = frutaService.listarFrutas();

        model.addAttribute("listarFrutas", frutas);
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
    public String doSalvar(@ModelAttribute @Valid Fruta f, Errors errors, RedirectAttributes redirectAttributes ){

        if (errors.hasErrors()){
            return "cadastrarPage";
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Operação concluída com sucesso.");

            frutaService.salvarFruta(f);
            return "redirect:/admin";
        }
    }

    @GetMapping(value = "/deletar/{id}")
    public String deletarFruta(@PathVariable long id, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("mensagem", "A fruta foi deletada com sucesso.");

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

    @GetMapping("/sobre")
    public String getSobrePage(Model model) {

        return "sobrePage.html";
    }
    @GetMapping("/admin")
    public String getAdminPage(Model model) {




        List<Fruta> frutas = frutaService.listarFrutas();
        model.addAttribute("listarFrutas", frutas);
        return "admFrutaPage";
    }

}

