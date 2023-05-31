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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {
    FrutaService frutaService;


    public CarrinhoController(FrutaService frutaService) {
        this.frutaService = frutaService;
    }

    @GetMapping("/adicionarCarrinho")
    public void adicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // Atualiza o valor do carrinho no cookie
            int valorCarrinho = carrinho.size();
            Cookie cookie = new Cookie("visita", String.valueOf(valorCarrinho));

            cookie.setMaxAge(24 * 60 * 60); // Define a duração do cookie para 24 horas
            response.addCookie(cookie);
        }

        // Redireciona para a página index
        response.sendRedirect("/index");
    }



    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // Verifica se a sessão existe e se o carrinho está presente
        if (session != null) {
            List<Fruta> carrinho = (List<Fruta>) session.getAttribute("carrinho");
            System.out.println("TEM FRUTA AQUI?" + carrinho);

            // Verifica se o carrinho está vazio
            if (carrinho != null && !carrinho.isEmpty()) {
                // Adiciona o carrinho ao modelo
                model.addAttribute("carrinho", carrinho);
            } else {
                // Se o carrinho estiver vazio, adiciona uma mensagem ao modelo
                model.addAttribute("mensagem", "Carrinho vazio");
            }
        } else {
            // Sessão não encontrada, adiciona uma mensagem de erro ao modelo
            model.addAttribute("mensagem", "Erro de sessão");
        }

        // Retorna o nome da página Thymeleaf a ser exibida
        return "carrinhoCompra";
    }



    @GetMapping("/finalizarCompra")
    public void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect("/index");
    }

}