<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pt.ecl.Html" %>
<!DOCTYPE html>
<html lang="pt">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Acesso reservado | ECL</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilo.css">
</head>
<body>
  <main class="confirmacao">
    <p class="eyebrow">ESCOLA DO COMÉRCIO DE LISBOA</p>
    <h1>Área de gestão</h1>
    <p>Introduza a palavra-passe de administrador para consultar e gerir os pedidos de assistência.</p>
    <% if (request.getAttribute("erro") != null) { %>
      <p class="erro" role="alert"><%= Html.escapar(request.getAttribute("erro")) %></p>
    <% } %>
    <form class="formulario" method="post" action="<%= request.getContextPath() %>/login">
      <label for="password">Palavra-passe de administrador</label>
      <input id="password" name="password" type="password" required autocomplete="current-password">
      <button type="submit">Entrar</button>
    </form>
    <a href="<%= request.getContextPath() %>/">Voltar ao início</a>
  </main>
</body>
</html>
