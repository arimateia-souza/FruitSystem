package tads.ufrn.provapw2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
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
            Cookie cookie = new Cookie("carrinho", String.valueOf(valorCarrinho));
            cookie.setMaxAge(24 * 60 * 60); // Define a duração do cookie para 24 horas
            response.addCookie(cookie);
        }

        // Redireciona para a página index
        response.sendRedirect("/index");
    }



    @GetMapping("/verCarrinho")
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        // Verifica se a sessão existe e se o carrinho está presente
        if (session != null) {
            List<Fruta> carrinho = (List<Fruta>) session.getAttribute("carrinho");

            // Verifica se o carrinho está vazio
            if (carrinho != null && !carrinho.isEmpty()) {
                // Lista os itens no carrinho
                for (Fruta fruta : carrinho) {
                    System.out.println(fruta);
                }
            } else {
                // Se o carrinho estiver vazio, redireciona para '/index' com uma mensagem de carrinho vazio
                response.sendRedirect("/index?msg=Carrinho vazio");
                return;
            }
        } else {
            // Sessão não encontrada, redireciona para /index com uma mensagem de erro
            response.sendRedirect("/index?msg=Erro de sessão");
            return;
        }

        // Adiciona o link para a rota /finalizarCompra
        response.sendRedirect("/finalizarCompra");
    }


    @GetMapping("/finalizarCompra")
    public void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        // Invalida a sessão existente
        if (session != null) {
            session.invalidate();
        }

        // Redireciona para a página index
        response.sendRedirect("/index");
    }

}