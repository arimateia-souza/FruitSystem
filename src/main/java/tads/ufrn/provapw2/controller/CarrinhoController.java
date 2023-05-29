package tads.ufrn.provapw2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tads.ufrn.provapw2.model.Fruta;
import tads.ufrn.provapw2.service.FrutaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {
    FrutaService frutaService;


    public CarrinhoController(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @GetMapping("/adicionarCarrinho")
    public void adicionarAoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o ID do item a ser adicionado ao carrinho a partir dos parâmetros da solicitação
        Long id = Long.parseLong(request.getParameter("id"));

        // Busca o item no banco de dados pelo ID
        Optional<Fruta> frutaOptional = frutaService.buscarFrutaPorId(id);

        // Verifica se o item foi encontrado
        if (frutaOptional.isPresent()) {
            Fruta fruta = frutaOptional.get();
            HttpSession session = request.getSession(true);

            // Obtém o carrinho da sessão ou cria um novo carrinho caso não exista
            List<Fruta> carrinho = (List<Fruta>) session.getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new ArrayList<>();
                session.setAttribute("carrinho", carrinho);
            }

            // Adiciona a fruta ao carrinho
            carrinho.add(fruta);
        }

        // Redireciona para a página index
        response.sendRedirect("/index");
    }




//    @GetMapping("/finalizarCompra")
//    public String finalizarCompra(HttpServletRequest request, HttpServletResponse response){
//        Cookie carrinhoCompras = new Cookie("carrinhoCompras", "");
//        response.addCookie(carrinhoCompras);
//        return "redirect:/index";
//    }



}