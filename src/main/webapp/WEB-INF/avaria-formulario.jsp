<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pt.ecl.Html" %>
<!DOCTYPE html>
<html lang="pt">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Comunicar avaria</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilo.css">
</head>
<body>
  <a href="<%= request.getContextPath() %>/">Página inicial</a>
  <h1>Comunicar avaria informática</h1>
  <p>Preencha os três campos para validar o pedido.</p>

  <form method="post" action="<%= request.getContextPath() %>/validar-avaria">
    <label for="sala">Sala</label>
    <input id="sala" name="sala" type="text" required
      value="<%= Html.escapar(request.getAttribute("sala")) %>">
    <% if (request.getAttribute("erroSala") != null) { %>
      <p class="erro" role="alert"><%= Html.escapar(request.getAttribute("erroSala")) %></p>
    <% } %>

    <label for="tipo">Tipo de avaria</label>
    <select id="tipo" name="tipo" required>
      <option value="">Selecione uma opção</option>
      <% String tipoAtual = request.getAttribute("tipo") == null
          ? "" : request.getAttribute("tipo").toString(); %>
      <% for (String opcao : new String[] {"Equipamento", "Software", "Rede"}) { %>
        <option value="<%= Html.escapar(opcao) %>"
          <%= opcao.equals(tipoAtual) ? "selected" : "" %>><%= Html.escapar(opcao) %></option>
      <% } %>
    </select>
    <% if (request.getAttribute("erroTipo") != null) { %>
      <p class="erro" role="alert"><%= Html.escapar(request.getAttribute("erroTipo")) %></p>
    <% } %>

    <label for="descricao">Descrição</label>
    <textarea id="descricao" name="descricao" required minlength="10"><%= Html.escapar(request.getAttribute("descricao")) %></textarea>
    <% if (request.getAttribute("erroDescricao") != null) { %>
      <p class="erro" role="alert"><%= Html.escapar(request.getAttribute("erroDescricao")) %></p>
    <% } %>

    <button type="submit">Validar pedido</button>
  </form>
</body>
</html>
