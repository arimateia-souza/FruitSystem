package tads.ufrn.provapw2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.model.Usuario;
import tads.ufrn.provapw2.service.FrutaService;
import tads.ufrn.provapw2.service.UsuarioService;

import java.util.List;


@Controller
public class UsuarioController {

    UsuarioService service;
    FrutaService frutaService;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/cadastrarUsuario")
    public String doCadastrarUsuario(Model model){

        Usuario u = new Usuario();
        model.addAttribute("usuario", u);

        return "cadastrarUsuario";
    }

    @PostMapping("/doSalvarUsuario")
    public String doSalvarUsuario(@ModelAttribute Usuario u){
        service.create(u);

        return "redirect:/login";
    }

    @GetMapping("/listUsuarios")
    public String listAll(){
        List<Usuario> usuarios = service.listAll();
        for (Usuario u : usuarios){
            System.out.println(u);
        }

        return "listUsuarios";
    }
}
