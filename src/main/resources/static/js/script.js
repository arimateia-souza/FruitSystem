// script.js
/* Obtém o valor do cookie "carrinho" */
var visitaValue = '';
var cookies = document.cookie.split(';');
for (var i = 0; i < cookies.length; i++) {
    var cookie = cookies[i].trim();
    if (cookie.startsWith('visita=')) {
        visitaValue = cookie.substring('visita='.length);
        break;
    }
}

// Exibe o valor do carrinho
document.getElementById('carrinho-quantidade').textContent = visitaValue;