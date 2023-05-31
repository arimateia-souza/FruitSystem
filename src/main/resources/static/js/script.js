// script.js
/* Obtém o valor do cookie "carrinho" */
var carrinhoValue = '';
var cookies = document.cookie.split(';');
for (var i = 0; i < cookies.length; i++) {
    var cookie = cookies[i].trim();
    if (cookie.startsWith('carrinho=')) {
        carrinhoValue = cookie.substring('carrinho='.length);
        break;
    }
}

// Exibe o valor do carrinho
document.getElementById('carrinho-quantidade').textContent = carrinhoValue;
